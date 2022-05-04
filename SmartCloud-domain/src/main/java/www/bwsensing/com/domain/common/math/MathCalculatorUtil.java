package www.bwsensing.com.domain.common.math;


import static www.bwsensing.com.domain.common.math.SelfMathFormulaEnum.getSelfMathFormulaEnum;
import static www.bwsensing.com.domain.common.math.SelfMathFormulaEnum.getSelfMathFormulaNames;
import org.apache.commons.lang3.StringUtils;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import javax.script.ScriptEngine;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.math.BigDecimal;
import java.util.*;


/**
 * @author macos-zyj
 */
@Slf4j
public class MathCalculatorUtil {
    private static final Set<Character> OPERATE_SET = new HashSet<>(Arrays.asList('+', '-', '*', '/', '%'));

    /**
     * JavaScript脚本引擎，Java SE 6开始支持
     **/
    private static final ScriptEngine SCRIPT_ENGINE = new ScriptEngineManager().getEngineByName("JavaScript");


    /**
     * @param str
     * @return boolean
     * @Description 判断字符串是否为数字(浮点类型也包括, 以及正负符号)
     * @Date 2020/12/25 18:18
     **/
    public static boolean isNumber(String str) {
        String reg = "^[-\\+]?[0-9]+(.[0-9]+)?$";
        return str.matches(reg);
    }


    /**
     * @param mathFormulaScript 数学公式字符串，如：mathFormula = (2*3-45/5+(9)+9%5 +2*(1+2) + Math.sqrt(3))/9.0
     *                          注意：如果使用开根号等三角函数等一些高级计算，则使用Math中字符串来替代，如4开根号==>Math.sqrt(4)
     *                          具体使用查看：https://www.w3school.com.cn/jsref/jsref_obj_math.asp
     * @param retainDigit       保留几位小数
     * @return java.lang.String
     * @Description 简单公式计算，会有精度丢失问题
     * @Date 2020/12/23 13:13
     **/
    public static String simpleFormulaScript(String mathFormulaScript, int retainDigit) {
        try {
            if (StringUtils.isNotEmpty(mathFormulaScript)) {
                mathFormulaScript = "(" + mathFormulaScript + ").toFixed(" + retainDigit + ")";
            }
            return JSON.toJSONString(SCRIPT_ENGINE.eval(mathFormulaScript));
        } catch (ScriptException e) {
            log.error("非法数学公式!");
            e.printStackTrace();
            throw new RuntimeException("非法数学公式！");
        }
    }

    /**
     * @param mathFormulaScript
     * @return java.lang.String
     * @Description 重载方法，默认进度小数保留两位
     * @Date 2020/12/23 13:48
     **/
    public static String simpleFormulaScript(String mathFormulaScript) {
        return simpleFormulaScript(mathFormulaScript, 2);
    }


    /**
     * @param arg
     * @return void
     * @Description 参数检查
     * @Date 2020/12/30 13:37
     **/
    private static void checkArg(String arg) {
        checkArg(arg, true);
    }

    /**
     * @param arg
     * @return void
     * @Description 参数检查
     * @Date 2020/12/30 13:37
     **/
    private static void checkArg(String arg, boolean isZero) {
        if (StringUtils.isEmpty(arg) || !isNumber(arg)) {
            throw new RuntimeException("非法计算参数！");
        }
        if ((!isZero && (new BigDecimal(BigInteger.ZERO).compareTo(new BigDecimal(arg)) == 0))) {
            throw new RuntimeException("非法计算参数！");
        }

    }

    /**
     * @param expression "1+1*2+(10-(2*(5-3)*(2-1))-4)+10/(5-0) + log(10) + log(10,12)"
     * @return void
     * @Description 校验公式表达式的合法性
     * @Date 2020/12/30 14:23
     **/
    private static void checkFormulaExpression(String expression) {
        //去除空格
        expression = expression.replaceAll(" ", "");
        //拆分字符串
        char[] arr = expression.toCharArray();
        int len = arr.length;
        //前后括号计数，用来判断括号是否合法
        int checkNum = 0;
        //数字集合
        StringBuffer sb = new StringBuffer();
        //字母集合
        StringBuffer sb0 = new StringBuffer();
        //循环
        for (int i = 0; i < len; i++) {
            //判断当前元素是否是数字
            if (Character.isDigit(arr[i]) || arr[i] == '.') {
                //把数字和小数点加入到集合中，为了下一步判断数字是否合法
                sb.append(arr[i]);
            } else if (Character.isLetter(arr[i])) {
                //校验自定义的公式（自定义的数学表达式都是使用字母拼接起来）
                sb0.append(arr[i]);
            } else {
                //如果sb中有值，取出来判断这个数字整体是否合法
                if (sb.length() > 0) {
                    if (isNumber(sb.toString())) {
                        //如果合法，清空，为了判断下一个
                        sb.setLength(0);
                    } else {
                        throw new RuntimeException("非法数字参数");
                    }
                }
                //不是数字为字符，可能是{}，[], (), +, - , * , /, % 等，再加上各种自定义的数学运算公式, 或者是其他不合法的字符，接着继续判断
                if (arr[i] == '+' || arr[i] == '*' || arr[i] == '/' || arr[i] == '%') {
                    //判断规则(1.不能位于首位 2.不能位于末尾 3.后面不能有其他运算符,但可以有-号 4.后面不能有后括号)
                    if (i == 0 || i == (len - 1) || arr[i + 1] == '+' || arr[i + 1] == '*' || arr[i + 1] == '/' || arr[i + 1] == '%' || arr[i + 1] == ')') {
                        log.error("非法符号 : '+' or '*' or '/' ->" + arr[i]);
                        throw new RuntimeException("非法符号 : '+' or '*' or '/' ==>" + arr[i]);
                    }
                } else if (arr[i] == '-') {
                    //减号判断规则(1.不能位于末尾 2.后面不能有其他运算符，但可以有-号 3.后面不能有后括号)
                    if (i == (len - 1) || arr[i + 1] == '+' || arr[i + 1] == '*' || arr[i + 1] == '/' || arr[i + 1] == '%' || arr[i + 1] == ')') {
                        log.error("非法符号 : '-' ->" + arr[i]);
                        throw new RuntimeException("非法符号 : '-'  ==>" + arr[i]);
                    }
                } else if (arr[i] == '(') {
                    //判断(括号前面是否有字母
                    //如果sb0中有字母，取出来判断这个字符串整体是否是自定义的公式字符
                    if (sb0.length() > 0) {
                        //从当前匹配的(,找到最近的),然后在求出在 这两个括号之间以英文逗号隔开的参数个数
                        int beginIndex = expression.indexOf(arr[i], i);

                        int endIndex = matchBracketIndex(expression, i, arr[i]);
                        if (endIndex == -1) {
                            log.error("非法数学公式符号:==>" + sb0.length());
                            throw new RuntimeException("非法数学公式符号: ==>" + sb0.length());
                        }
                        //截取字符串，且分隔匹配的英文逗号
                        String selfMathBracketContentStr = expression.substring(beginIndex + 1, endIndex);
                        if (StringUtils.isEmpty(selfMathBracketContentStr)) {
                            log.error("非法自定义数学公式符号:==>" + sb0.length());
                            throw new RuntimeException("非法自定义数学公式符号: ==>" + sb0.length());
                        }
                        //获取参数个数
                        StringBuilder selfMathBracketContentSb = new StringBuilder(selfMathBracketContentStr);
                        int argCounts = getSelfMathMarkArgCounts(selfMathBracketContentSb, ",");
                        //校验自定义公式的合法性
                        checkSelfMathMark(sb0.toString(), argCounts);
                        //清空内容
                        sb0.setLength(0);
                    }
                    checkNum++;
                    //判断规则(1.不能位于末尾 2.后面不能有+，*，/,%运算符和后括号 3.前面不能为数字)
                    if (i == (len - 1) || arr[i + 1] == '+' || arr[i + 1] == '*' || arr[i + 1] == '/' || arr[i + 1] == '%' || arr[i + 1] == ')' || (i != 0 && Character.isDigit(arr[i - 1]))) {
                        log.error("非法符号 : '(' ->" + arr[i]);
                        throw new RuntimeException("非法符号 : '('  ==>" + arr[i]);
                    }
                } else if (arr[i] == ')') {
                    checkNum--;
                    //判定规则(1.不能位于首位 2.后面不能是前括号 3.括号计数不能小于0，小于0说明前面少了前括号)
                    if (i == 0 || (i < (len - 1) && arr[i + 1] == '(') || checkNum < 0) {
                        log.error("非法符号 : ')' ->" + arr[i]);
                        throw new RuntimeException("非法符号 : ')'  ==>" + arr[i]);
                    }
                } else if (arr[i] == ',') {
                    //判定规则，如果有逗号，1，匹配该逗号是否被括号()包着，且左括号当前前面是否是自定义公式
                    checkComma(expression, i, i);
                } else {
                    //非数字和运算符
                    log.error("非数字和运算符:==>" + arr[i]);
                    throw new RuntimeException("非数字和运算符:==>" + arr[i]);
                }
            }
        }
        //不为0,说明括号不对等，可能多前括号
        if (checkNum != 0) {
            //非数字和运算符
            log.error("括号个数不匹配");
            throw new RuntimeException("括号个数不匹配");
        }
    }

    /**
     * @param str               待匹配的字符数组
     * @param currentCommaIndex 当前逗号索引
     * @param constCommaIndex   常量逗号索引
     * @return void
     * @Description 校验英文逗号
     * @Date 2020/12/31 13:24
     **/
    private static void checkComma(String str, int currentCommaIndex, final int constCommaIndex) {
        //从currentCommaIndex索引开始往前找最近的一个左括号(,求出索引，并求出对应的)索引
        int beginIndex = indexOfBefore(str, currentCommaIndex, '(');
        if (beginIndex == -1) {
            log.error("非法逗号！");
            throw new RuntimeException("非法逗号！");
        }
        int endIndex = matchBracketIndex(str, beginIndex, '(');
        if (endIndex == -1) {
            log.error("非法逗号！");
            throw new RuntimeException("非法逗号！");
        }
        //找到符合的逗号条件
        if (endIndex <= constCommaIndex) {
            //接着往上找
            checkComma(str, beginIndex, constCommaIndex);
        } else {
            //在从beginIndex索引开始往前找出连续字母的字符，然后拼接
            char[] chars = str.toCharArray();
            StringBuilder sb = new StringBuilder();
            for (int i = beginIndex - 1; i >= 0; i--) {
                //如果是字母或者是数字
                if (Character.isLetter(chars[i]) || Character.isDigit(chars[i])) {
                    sb.append(chars[i]);
                } else {
                    break;
                }
            }
            //将sb带入自定义公式进行校验
            List<String> selfMathFormulaNames = getSelfMathFormulaNames();
            if (!selfMathFormulaNames.contains(sb.reverse().toString())) {
                throw new RuntimeException("非法逗号！");
            }
        }
    }

    /**
     * @param str      目标字符串 如 abcd((a),(b,bb))dada
     * @param endIndex
     * @return int
     * @Description 返回从结束索引结束，
     * @Date 2020/12/31 13:36
     **/
    private static int indexOfBefore(String str, int endIndex, char dest) {
        char[] chars = str.trim().toCharArray();
        int len = chars.length;
        int index0 = endIndex;
        if (len - 1 < endIndex) {
            index0 = len;
        }
        for (int i = index0 - 1; i >= 0; i--) {
            if (chars[i] == dest) {
                return i;
            }
        }
        //没有找到返回-1
        return -1;
    }

    /**
     * @param mathArgStr 参数括号字符串 如：log((math(1,6)+2),(8*9)) 中的 ((math(1,6)+2),(8*9))
     * @param regex      分隔符 默认一般英文逗号,
     * @return void
     * @Description 获取自定义公式的参数个说
     * @Date 2020/12/31 10:30
     **/
    private static int getSelfMathMarkArgCounts(StringBuilder mathArgStr, String regex) {
        //找到最近的一个( 索引
        int beginIndex = mathArgStr.indexOf("(");
        if (beginIndex != -1) {
            int endIndex = matchBracketIndex(mathArgStr.toString(), beginIndex, '(');
            if (endIndex == -1) {
                throw new RuntimeException("非法括号匹配！");
            }
            mathArgStr.replace(beginIndex, endIndex + 1, "");
            return getSelfMathMarkArgCounts(mathArgStr, regex);
        } else {
            //如果没有匹配的做扩号，则直接求出参数个数
            String[] argsArr = mathArgStr.toString().split(regex, -1);
            return argsArr.length;
        }
    }


    /**
     * @param mathStr  log  sin
     * @param argCount 2  , 1
     * @return boolean
     * @Description 校验数字字符数组
     * @Date 2020/12/30 15:09
     **/
    private static void checkSelfMathMark(String mathStr, int argCount) {
        SelfMathFormulaEnum selfMathFormulaEnum = getSelfMathFormulaEnum(mathStr, argCount);
        if (selfMathFormulaEnum == null) {
            throw new RuntimeException("自定义数学公式不匹配！");
        }
    }

    /**
     * @param s         待匹配的字符串
     * @param fromIndex 开始索引
     * @param leftDest  左括号
     * @return int
     * @Description 通过当前左括号(的索引 ， 找到与之匹配对应的右括号) 的索引
     * @Date 2020/12/30 17:23
     **/
    private static int matchBracketIndex(String s, int fromIndex, char leftDest) {
        if (StringUtils.isEmpty(s)) {
            return -1;
        }
        //取出匹配目标的第一个索引
        int index0 = s.indexOf(leftDest, fromIndex);
        if (index0 != -1) {
            //1、申明一个stack
            Stack<Character> stack = new Stack<>();
            //遍历s String本质上是char[]
            for (int i = index0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '{' || c == '[' || c == '(') {
                    //如果是{ [ (  压入栈中
                    stack.push(c);
                } else if (c == '}' || c == ']' || c == ')') {
                    //  }  ]  )   进行比对,
                    if (stack.isEmpty()) {
                        return -1;
                    }
                    char topChar = stack.pop();
                    if ((topChar == '[' && c == ']') || (topChar == '(' && c == ')') || (topChar == '{') && c == '}') {
                        if (stack.isEmpty()) {
                            return i;
                        }
                    }
                }
            }
        }
        return -1;
    }


    /**
     * @param v1
     * @param v2
     * @return java.lang.String
     * @Description 两个数相加
     * @Date 2020/12/30 13:42
     **/
    private static String add(String v1, String v2) {
        //校验参数
        checkArg(v1);
        checkArg(v2);
        BigDecimal v1Bd = new BigDecimal(v1);
        BigDecimal v2Bd = new BigDecimal(v2);
        return v1Bd.add(v2Bd).toString();
    }

    /**
     * @param v1
     * @param v2
     * @return java.lang.String
     * @Description 两个数相减
     * @Date 2020/12/30 13:47
     **/
    private static String sub(String v1, String v2) {
        //校验参数
        checkArg(v1);
        checkArg(v2);
        BigDecimal v1Bd = new BigDecimal(v1);
        BigDecimal v2Bd = new BigDecimal(v2);
        return v1Bd.subtract(v2Bd).toString();
    }

    /**
     * @param v1
     * @param v2
     * @return java.lang.String
     * @Description 两个数向乘
     * @Date 2020/12/30 13:49
     **/
    private static String mul(String v1, String v2) {
        //校验参数
        checkArg(v1);
        checkArg(v2);
        BigDecimal v1Bd = new BigDecimal(v1);
        BigDecimal v2Bd = new BigDecimal(v2);
        return v1Bd.multiply(v2Bd).toString();
    }

    /**
     * @param v1
     * @param v2
     * @return java.lang.String
     * @Description 两个数相除
     * @Date 2020/12/30 13:50
     **/
    private static String div(String v1, String v2) {
        //校验参数
        checkArg(v1);
        //除数不能为0
        checkArg(v2, false);
        BigDecimal v1Bd = new BigDecimal(v1);
        BigDecimal v2Bd = new BigDecimal(v2);
        return v1Bd.divide(v2Bd, 2, RoundingMode.HALF_UP).toString();
    }

    /**
   
     * @Description  v1%v2 取余
     * @Date 2021/1/4 22:14
     * @param v1
     * @param v2
     * @return java.lang.String
     **/
    private static String mod(String v1, String v2) {
        //校验参数
        checkArg(v1);
        //除数不能为0
        checkArg(v2, false);
        BigDecimal v1Bd = new BigDecimal(v1);
        BigDecimal v2Bd = new BigDecimal(v2);
        return v1Bd.remainder(v2Bd).toString();
    }




    /**
     * @param mathFormula 公式字符串， 如："1+1*2+(10-(2*(5-3)*(2-1))-4)+10/(5-0) + log(10) + log(10,12)"
     *                    注意log(10) ==> ln(10) ==> log(e,10) 表示以自然数e为底的对数
     * @return java.lang.String
     * @Description 使用递归计算，判断表达式是否有括号，有括号，则先计算括号内的表达式，无则直接运算结果。
     * @Date 2020/12/30 14:07
     **/
    public static String calculator(String mathFormula) {
        if (StringUtils.isEmpty(mathFormula)) {
            throw new RuntimeException("非法计算公式！");
        }
        //替换空格
        mathFormula = mathFormula.replaceAll(" ", "");
        int bracket = mathFormula.indexOf("[");
        int brace = mathFormula.indexOf("{");
        if (bracket != -1 || brace != -1) {
            //将字符串中的"{}"、"[]"替换成"()"
            log.info("计算公式：{}", mathFormula);
            mathFormula = mathFormula.replaceAll("[\\[\\{]", "(").replaceAll("[\\]\\}]", ")");
            log.info("标准数学计算公式 '{,[':" + mathFormula);
        }
        //校验公式参数是否合法
        checkFormulaExpression(mathFormula);
        //==================================================开始计算=============================================
        //计算思路：以下是计算顺序
        // 1，如果有自定义的数学公式，则先计算自定义的公式
        // 2, 如果有括号，则先计算括号内的（去括号）
        // 3, 没有括号直接计算
        String result0 = calculatorSelfMathFormula(mathFormula);
        //结果保留八位小数
        return new BigDecimal(result0).setScale(8,BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * @Description  去除自定义公式，todo 待优化
     * @Date 2020/12/31 16:49
     * @param mathFormula
     * @return java.lang.String
     **/
    private static String calculatorSelfMathFormula(String mathFormula) {
        if (StringUtils.isEmpty(mathFormula)) {
            throw new RuntimeException("非法参数错误！");
        }
        mathFormula = mathFormula.replaceAll(" ", "");
        //去除自定义公式
        List<SelfMathFormulaEnum> selfMathFormulaEnums = SelfMathFormulaEnum.getSelfMathFormulas();
        boolean flag = false;
        for (SelfMathFormulaEnum mathFormulaEnum : selfMathFormulaEnums) {
            if (mathFormula.contains(mathFormulaEnum.getFormulaName())) {
                flag = true;
                break;
            }
        }
        //包含自定义公式
        if (flag) {
            for (int i = 0; i < selfMathFormulaEnums.size();) {
                boolean repeat = false;
                SelfMathFormulaEnum mathFormulaEnum = selfMathFormulaEnums.get(i);
                //如果该公式表达式包含自定义数学公式  "1+1*2+(10-(2*(5-3)*(2-1))-4)+10/(5-0) + log(10) + 2*log(30)+ max(sin(2*(5+2))*6,pow(2*(2+8),3+2)) * max(1+3,(3-5)) + min(sin(10), sin(20))"
                if (mathFormula.contains(mathFormulaEnum.getFormulaName())) {
                    //如果匹配到，则获取第一个自定义的数学公式首字母所在的索引（该索引是格式化后的索引）
                    int index0 = mathFormula.indexOf(mathFormulaEnum.getFormulaName());
                    //取出该公式括号中内容字符,不包括左右字符
                    String left = mathFormula.substring(0, index0);
                    int index1 = matchBracketIndex(mathFormula, index0, '(');
                    String right = mathFormula.substring(index1 +1);
                    String bracketsContent = mathFormula.substring(index0 + mathFormulaEnum.getFormulaNameLength() + 1, index1);
                    //计算括号中的值，如果该字符串又包含其他自定义公式，则递归继续计算
                    //left + result0 + right
                    mathFormula =  left + selfMathCalculation(mathFormulaEnum.getFormulaName(), calculatorSelfMathFormula(bracketsContent)) + right;
                    repeat = true;
                }
                if (!repeat) {
                    i++;
                }
            }
        }
        //直接进行计算
        return standardCalculation(mathFormula);
    }

    /**
     * @Description  自定义公式计算
     * @Date 2020/12/31 17:07
     * @param mathFormulaName 自定义公式名称
     * @param digitStr 一个具体的数值
     * @return java.lang.String
     **/
    private static String selfMathCalculation(String mathFormulaName, String digitStr) {
        double result;
        if (StringUtils.isEmpty(digitStr)) {
            throw new RuntimeException("非法计算公式参数！");
        }
        String[] args = digitStr.split(",", -1);
        SelfMathFormulaEnum selfMathFormulaEnum = getSelfMathFormulaEnum(mathFormulaName);
        if (selfMathFormulaEnum == null) {
            throw new RuntimeException("非法数学公式名称");
        }
        switch (selfMathFormulaEnum) {
            case abs:
                result = Math.abs(Double.parseDouble(args[0]));
                break;
            case acos:
                result = Math.acos(Double.parseDouble(args[0]));
                break;
            case asin:
                result =  Math.asin(Double.parseDouble(args[0]));
                break;
            case atan:
                result = Math.atan(Double.parseDouble(args[0]));
                break;
            case ceil:
                result = Math.ceil(Double.parseDouble(args[0]));
                break;
            case cos:
                result = Math.cos(Double.parseDouble(args[0]));
                break;
            case exp:
                result = Math.exp(Double.parseDouble(args[0]));
                break;
            case floor:
                result = Math.floor(Double.parseDouble(args[0]));
                break;
            case log:
                result = Math.log(Double.parseDouble(args[0]));
                break;
            case max:
                result = Math.max(Double.parseDouble(args[0]), Double.parseDouble(args[1]));
                break;
            case min:
                result = Math.min(Double.parseDouble(args[0]), Double.parseDouble(args[1]));
                break;
            case pow:
                result = Math.pow(Double.parseDouble(args[0]), Double.parseDouble(args[1]));
                break;
            case round:
                result = Math.round(Double.parseDouble(args[0]));
                break;
            case sin:
                result = Math.sin(Double.parseDouble(args[0]));
                break;
            case sqrt:
                result = Math.sqrt(Double.parseDouble(args[0]));
                break;
            case tan:
                result = Math.tan(Double.parseDouble(args[0]));
                break;
            default:
                throw new RuntimeException("找不到匹配的计算公式！");
        }
        return String.valueOf(result);
    }


    /**
   
     * @Description  标准计算，不包含自定义函数, 但包含括号与其他符号表达式
     * @Date 2020/12/31 17:02
     * @param str
     * @return java.lang.String
     **/
    private static String standardCalculation(String str) {
        if (StringUtils.isEmpty(str)) {
            log.error("非法计算公式！");
            throw new RuntimeException("非法计算公式！");
        }
        String[] args = str.split(",", -1);
        if (args.length > 0) {
            List<String> argResult = new ArrayList<>();
            for (String arg : args) {
                //每一个arg 都是一个算式（带上括号的）
                //判断是公式表达是是否存在小括号（优先级）
                int hasBrackets = arg.lastIndexOf('(');
                if (hasBrackets == -1) {
                    //没有小括号，直接计算
                    argResult.add(cac(arg));
                }else {
                    int cr = arg.indexOf(')', hasBrackets);
                    String left = arg.substring(0, hasBrackets);
                    String right = arg.substring(cr + 1);
                    //如果存在"("提取括号中的表达式
                    String middle = arg.substring(hasBrackets + 1, cr);
                    argResult.add(standardCalculation(left + cac(middle) + right));
                }
            }
            return StringUtils.join(argResult, ",");
        }
        throw new RuntimeException("非法算式参数！");
    }

    /**
     * DESC:计算表达式，判断是否存在乘除运算，存在则先执行乘除运算，然后执行加减运算，返回运算结果；
     * 不存在，直接运行加减运算，返回运算结果。
     *
     * @param str  -2.8+8*3/2+0.9 或 -2.8*6
     * @return 运算结果
     */
    private static String cac(String str) {
        //字符串中不存在*，/, %
        int mulIndex = str.indexOf('*');
        int divIndex = str.indexOf('/');
        int modIndex = str.indexOf('%');
        //只有加法和减法
        if (mulIndex == -1 && divIndex == -1 && modIndex == -1) {
            return aasOperation(str);
        }
        String result0 = "0";
        //定义先处理的符号索引位置
        int index0 = getMin(-1,mulIndex, divIndex, modIndex);
        try {
            String left = str.substring(0, index0);
            String v1 = lastNumber(left);
            left = left.substring(0, left.length() - v1.length());
            String right = str.substring(index0 + 1);
            String v2 = firstNumber(right);
            right = right.substring(v2.length());

            if (index0 == mulIndex) {
                result0 = mul(v1, v2);
            } else if(index0 == divIndex) {
                result0 = div(v1, v2);
            } else if(index0 == modIndex) {
                result0 = mod(v1, v2);
            }
            String s = left + result0 + right;
            return cac(left + result0 + right);
        }catch (Exception e) {
            log.error("数学计算公式错误"+ e.getMessage());
            throw new RuntimeException("数学计算公式错误！");
        }
    }
    /**
     * @Description 求给定可变参数中不等于noNum 的最小值
     * @Date 2021/1/5 10:49
     * @param noNum
     * @param a 可变参数
     * @return int
     **/
    private static int getMin(int noNum, int... a){
        if (a == null || a.length == 0) {
            throw new RuntimeException("非法参数！");
        }
        int min = a[0];
        for (int i = 1; i < a.length; i++) {
            if (min ==noNum || (min > a[i] && a[i] != noNum)) {
                min = a[i];
            }
        }
        if (min == noNum) {
            throw new RuntimeException("非法可变参数！");
        }
        return min;
    }


    /**
   
     * @Description 获得表达式的最后连续合法数字
     * @date 2021/1/6 17:49
     * @param str
     * @return java.lang.String
     */
    private static String lastNumber(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            char c = str.charAt(i);
            //包含首字母为-
            if (Character.isDigit(c) || (i != 0 && c == '.') || ((i == 0 || OPERATE_SET.contains(str.charAt(i -1))) && c == '-')) {
                sb.append(c);
            }else {
                break;
            }
        }
        return sb.reverse().toString();
    }


    /**
   
     * @Description 获得表达式的最后连续合法数字
     * @date 2021/1/6 17:49
     * @param str
     * @return java.lang.String
     */
    private static String firstNumber(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            //包含首字母为-
            if (Character.isDigit(c) || (i != 0 && c == '.') || (i == 0 && c == '-')) {
                sb.append(c);
            }else {
                break;
            }
        }
        return sb.toString();
    }
    /**
     * @Description  只用加减操作
     * @Date 2021/1/4 20:02
     * @param mathStr 只有加减操作的数学运算字符串： 如2.98-5-6+9-0.2-8  或  -2.0-9-5+9  或 -9-2
     * @return java.lang.String
     **/
    private static String aasOperation(String mathStr) {
        if (StringUtils.isEmpty(mathStr)) {
            throw new RuntimeException("非法计算参数");
        }
        //这里字符串加上一个运算法号，只要是合法的都可以，只是为了走一步运算
        char[] options = (mathStr + "+").replaceAll(" ", "").toCharArray();
        String result0 = "0";
        StringBuilder sb = new StringBuilder();
        char sign = '+';
        for (int i = 0; i < options.length; i++) {
            if (Character.isDigit(options[i]) || options[i] == '.') {
                sb.append(options[i]);
            } else {
                if ((i == 0 && options[i] == '-') || (i>1 && OPERATE_SET.contains(options[i-1]))) {
                    sb.append(options[i]);
                }else {
                    if (sb.length() > 0){
                        //先默认为 + 把第一个数值加上
                        if (sign == '+') {
                            result0 = add(result0, sb.toString());
                        } else {
                            result0 = sub(result0, sb.toString());
                        }
                        sb.setLength(0);
                        sign = options[i];
                    } else {
                        throw new RuntimeException("非法数学公式错误！");
                    }
                }
            }
        }
        return result0;
    }
}
