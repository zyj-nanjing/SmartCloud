package www.bwsensing.com.common.dynamic;
import com.alibaba.cola.exception.BizException;
import www.bwsensing.com.common.core.text.Convert;
import www.bwsensing.com.common.utills.PathUtil;
import www.bwsensing.com.common.utills.io.IoUtils;
import javax.tools.*;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * 动态编译工具类
 * @author macos-zyj
 */
public class DynamicCompileUtil {

    public static void compile() throws Exception {
        //类名
        String className = "BwAngleAnalyseExtPtExp";
        //项目所在路径
        String projectPath = PathUtil.getAppHomePath();
        System.out.println(projectPath);
        String facadeJarPath = String.format("../dynamic/class", projectPath);
        System.out.println(facadeJarPath);
        //接口的类加载器
        ClassLoader animalClassLoader = DynamicCompileUtil.class.getClassLoader();
        //设置当前的线程类加载器
        Thread.currentThread().setContextClassLoader(animalClassLoader);
        //需要进行编译的代码
        Iterable<? extends JavaFileObject> compilationUnits = new ArrayList<>() {{
            add(new JavaSourceFromString(className, getJavaCode()));
        }};
        //编译的选项，对应于命令行参数
        List<String> options = new ArrayList<>();
        options.add("-classpath");
        options.add(facadeJarPath);
        //使用系统的编译器
        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager standardJavaFileManager = javaCompiler.getStandardFileManager(null, null, null);
        ScriptFileManager scriptFileManager = new ScriptFileManager(standardJavaFileManager);
        //使用stringWriter来收集错误。
        StringWriter errorStringWriter = new StringWriter();
        //开始进行编译
        boolean ok = javaCompiler.getTask(errorStringWriter, scriptFileManager, diagnostic -> {
            if (diagnostic.getKind() == Diagnostic.Kind.ERROR) {
                errorStringWriter.append(diagnostic.toString());
            }
        }, options, null, compilationUnits).call();

        if (!ok) {
            String errorMessage = errorStringWriter.toString();
            //编译出错，直接抛错。
            throw new RuntimeException("Compile Error:{}" + errorMessage);
        }
        //获取到编译后的二进制数据。
        final Map<String, byte[]> allBuffers = scriptFileManager.getAllBuffers();
        final byte[] catBytes = allBuffers.get(className);

        //做安全检查，白名单黑名单限制
        safetyCheck(catBytes);


    }

    private static final String CHANGE_LINE = "\n";

    private static String getInputStreamToJavaBody(InputStream javaStream){
        Scanner scanner = new Scanner(javaStream, StandardCharsets.UTF_8);
        StringBuilder javaBody = new StringBuilder();
        while (scanner.hasNextLine()) {
            String currentLine= scanner.nextLine();
            javaBody.append(currentLine);
        }
        return javaBody.toString();
    }
    /**
     * Java类的对应代码
     */
    public static String getJavaCode() {
        try{
            String url = "https://beiwei-smart-cloud.oss-cn-hangzhou.aliyuncs.com/BwAngleAnalyseExtPtExp.java";
            String cacheResult = str(IoUtils.toByteArray(new URL(url).openStream()), StandardCharsets.UTF_8);
            assert cacheResult != null;
            InputStream javaStream = new ByteArrayInputStream(cacheResult.getBytes());
            return getInputStreamToJavaBody(javaStream);
        } catch (IOException ioException) {
            throw new BizException("IO异常！");
        }
    }

    public static String str(byte[] data, Charset charset)  {
        return Convert.str(data, charset);
    }



    private static boolean isEmpty(String str)
    {
        return isNull(str) || str.trim().length()<=0;
    }

    private static boolean isNull(Object object)
    {
        return object == null;
    }
    public static List<String> whiteList() {
        return new ArrayList<String>() {{
            add("java.lang");
            add("java.util");
            add("java.text");
            add("[D");
            add("[F");
            add("[j");
            add("[I");
            add("[J");
            add("[C");
            add("[B");
            add("[Z");
            //保证测试进行
            add("java.io.PrintStream");
            add("java.sql.Timestamp,");
            add("BwAngleAnalyseExtPtExp");
            add("java.sql.Timestamp");
            add("[Ljava.lang.String");
            add("www.bwsensing.com");
        }};
    }

    public static List<String> blackList() {
        return new ArrayList<>() {{
            add("java.lang.Thread");
        }};
    }


    public static void safetyCheck(byte[] catBytes) throws Exception {
        InputStream inputStream = new ByteArrayInputStream(catBytes);
        Set<String> dependencies = JavassistUtil.getDependencies(inputStream);
        inputStream.close();

        //判断是否在白名单中
        List<String> notSupportedPackages = new ArrayList<>();
        for (String d : dependencies) {
            boolean supported = false;
            for (String supportPackage : whiteList()) {
                if (d.startsWith(supportPackage)) {
                    supported = true;
                    break;
                }
            }
            if (!supported) {
                notSupportedPackages.add(d);
            }
        }

        //做黑名单限制
        for (String d : dependencies) {
            boolean unsupported = false;
            for (String supportPackage : blackList()) {
                if (d.startsWith(supportPackage)) {
                    unsupported = true;
                    break;
                }
            }
            if (unsupported) {
                notSupportedPackages.add(d);
            }
        }

        if (!notSupportedPackages.isEmpty()) {
            String message = String.join(",", notSupportedPackages);
            throw new RuntimeException("不支持以下类的使用：" + message);
        }
    }

    public static void main(String[] args) throws Exception {
        compile();
    }
}
