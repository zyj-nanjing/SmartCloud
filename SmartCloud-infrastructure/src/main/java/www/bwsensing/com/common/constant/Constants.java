package www.bwsensing.com.common.constant;

/**
 * 通用常量信息
 *
 * @author Mac_zyj
 */
public class Constants
{
  public  static final String UNDERLINE = "_";
  /**
   * UTF-8 字符集
   */
  public static final String UTF8 = "UTF-8";

  /**
   * GBK 字符集
   */
  public static final String GBK = "GBK";

  public static  final String LOCAL_HOST_IP ="127.0.0.1";

  /**
   * TINYINT:1字节,非常小的正整数
   */
  public static final Long UNSIGNED_TINYINT_MAX = 255L;

  public static final Long SIGNED_TINYINT_MAX = 127L;

  public static final Long SIGNED_TINYINT_MIN = -128L;
  /**
   * SMALLINT:2字节小整数
   */
  public static final Long UNSIGNED_SMALLINT_MAX = 65535L;

  public static final Long SIGNED_SMALLINT_MAX = 32767L;

  public static final Long SIGNED_SMALLINT_MIN = -32768L;
  /**
   * MEDIUMINT:3字节 中等大小的整数
   */
  public static final Long UNSIGNED_MEDIUMINT_MAX = 16777215L;

  public static final Long SIGNED_MEDIUMINT_MAX = 8388607L;

  public static final Long SIGNED_MEDIUMINT_MIN = -8388608L;
  /**
   * INT:4字节 标准整数
   */
  public static final Long  UNSIGNED_INT_MAX = 4294967295L;

  public static final Long  SIGNED_INT_MAX = 2147482647L;

  public static final Long  SIGNED_INT_MIN = -2147483648L;

  public static final String POST ="POST";

  public static final String GET = "GET" ;

  public static final String OPTIONS ="OPTIONS";

  /**
   * 通用成功标识
   */
  public static final String SUCCESS = "0";

  /**
   * 通用失败标识
   */
  public static final String FAIL = "1";
  /**
   * 定时任务白名单配置（仅允许访问的包名，如其他需要可以自行添加）
   */
  public static final String[] JOB_WHITELIST_STR = { "www.bwsensing.com" };

  /**
   * 定时任务违规的字符
   */
  public static final String[] JOB_ERROR_STR = { "java.net.URL", "javax.naming.InitialContext", "org.yaml.snakeyaml",
          "org.springframework", "org.apache", "www.bwsensing.com.utils.file" };
}
