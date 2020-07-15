package com.farben.readme;

import com.alibaba.fastjson.JSONObject;
import com.farben.readme.bean.EroadAdminConfig;
import com.farben.readme.bean.config.EroadConfig;
import com.farben.readme.config.EroadConstantsConfig;
import com.farben.readme.config.JwtConfig;
import com.farben.readme.config.LdapConfig;
import com.farben.readme.constant.Constant;
import com.farben.readme.exception.TokenException;
//import com.farben.readme.mapper.EroadAdminConfigMapper;
import com.farben.readme.service.ICacheService;
import com.farben.readme.service.ILoginService;
import com.farben.readme.service.ITokenService;
import com.farben.readme.util.aes.CoreSymmetricCryptographyUtil;
import io.jsonwebtoken.Claims;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EroadApplicationTests {

    private Logger logger = LoggerFactory.getLogger(getClass());

//    @Autowired
//    DataSource dataSource;

    @Autowired
    private ICacheService cacheService;

    @Autowired
    LdapConfig ldapConfig;

    @Autowired
    JwtConfig jwtConfig;

    @Autowired
    ILoginService loginService;

    @Autowired
    ITokenService tokenService;

//    @Autowired
//    EroadAdminConfigMapper eroadAdminConfigMapper;

    @Autowired
    EroadConstantsConfig eroadConstantsConfig;

//    @Test
//    public void contextLoads() throws SQLException {
//        System.out.println(dataSource.getClass());
//
//        Connection connection = dataSource.getConnection();
//        System.out.println(connection);
//        connection.close();
//
//        System.out.println("=======> system out println ...");
//
//        //log level
//        //from low to high   trace<debug<info<warn<error
//        //can adjust log level
//        logger.trace("======> trace log...");
//        logger.debug("======> debug log...");
//        //SpringBoot default info level
//        logger.info("======> info log...");
//        logger.warn("======> warn log...");
//        logger.error("======> error log...");
//    }

    @Test
    public void testCache() {
        cacheService.put("FeedBackCache", "test11111", "123123213");
        System.out.println(cacheService.get("FeedBackCache", "test11111"));
    }

    @Test
    public void test() {
        System.out.println(ldapConfig);
        System.out.println(jwtConfig);
        System.out.println(eroadConstantsConfig);
        System.out.println("===================");
    }

    /*@Test
    public void test_insertEroadAdminConfig() {
        EroadConfig eroadConfig = new EroadConfig();

        Section2 section2 = new Section2();
        section2.setTitle("Section 2:");
        section2.setValue("test section2");
        List<Part> partList = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            Part part = new Part();
            part.setChildName("Part " + i + ":");
            part.setChildValue("" + i);
            List<Product> productList = new ArrayList<>();
            for (int j = 1; j <= 4; j++) {
                Product product = new Product();
                product.setProductName("智能金融-" + j);
                product.setLink("www.baidu.com");
                product.setImgUrl("" + j);
                product.setImgUrl2("" + j);
                product.setImgUrl3("" + j);
                productList.add(product);
            }
            part.setItems(productList);
            partList.add(part);
        }
        section2.setItems(partList);
        eroadConfig.setSection2(section2);


        byte[] bytes = JSONObject.toJSONBytes(eroadConfig);

        EroadAdminConfig eroadAdminConfig = new EroadAdminConfig();
        eroadAdminConfig.setId(CommonUtils.uuid());
        eroadAdminConfig.setKey("home_page");
        eroadAdminConfig.setType("config");
        eroadAdminConfig.setCreatedBy("admin");
        eroadAdminConfig.setCreatedTime(new Date());
        eroadAdminConfig.setStatus(Constant.STATUS_TRUE);
        eroadAdminConfig.setValue(bytes);
        ;

        try {
//            File file = new File("com/hsbc/eroad/1.gif");
//            InputStream is = new FileInputStream(file);
//            bytes = new byte[is.available()];
//            is.read(bytes);
//            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            eroadAdminConfigMapper.insertEroadAdminConfig(eroadAdminConfig);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

//    @Test
//    public void test_getEroadAdminConfig() {
//
//        try {
//            EroadAdminConfig eroadAdminConfig = eroadAdminConfigMapper.getLast("99999999");
//
//            System.out.println(eroadAdminConfig.getId());
//            System.out.println(eroadAdminConfig.getKey());
//            System.out.println(eroadAdminConfig.getType());
//            byte[] value = eroadAdminConfig.getValue();
//            System.out.println(value.length);
//            EroadConfig eroadConfig = JSONObject.parseObject(value, EroadConfig.class);
////            System.out.println(eroadConfig.toString());
//            System.out.println(JSONObject.toJSONString(eroadConfig));
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }

    @Test
    public void test_generateAccessToken() throws TokenException {
        String param = Constant.TOKEN_SIGNATURE + "-" + System.currentTimeMillis();
        String encryptedRequest = CoreSymmetricCryptographyUtil.encryptData(Constant.AES_128_KEY, param);
        String token = loginService.generateAccessToken(encryptedRequest);
        System.out.println(token);
    }

    @Test
    public void test_generateLogonToken() throws TokenException {
        String staffId = "9999";
        String staffName = "9999name";
        String token = loginService.generateLogonToken(staffId, staffName);
        System.out.println(token);
    }

    @Test
    public void test08() throws Exception {
//        System.out.println(TimeZone.getDefault().getDisplayName());
//        System.out.println("TimeZone.getDefault().getID()=" + TimeZone.getDefault().getID());
//        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss (zzz) yyyy", Locale.getDefault());
//        String format = sdf.format(new Date());
//        System.out.println(format);

//        String staffId = "9999notExpired";
//        String staffName = "9999notExpired";
//        String token = loginService.generateTokenNotExpired(staffId, staffName);
//        System.out.println(token);

        String staffIdFromLogonToken = loginService.getStaffIdFromLogonToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdGFmZk5hbWUiOiI5OTk5bm90RXhwaXJlZCIsInN0YWZmSWQiOiI5OTk5bm90RXhwaXJlZCJ9.0JlR2S_4cEc661NGKKr8SHVDXTuj6zzgXMRfxUDuV5tIrrs32PUwoRWMEzKlCqZc5amibJIGIIyOajIP7eQrsw");
        System.out.println(staffIdFromLogonToken);

        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdGFmZlB3ZCI6ImhiY25jbWIyMDE5Iiwic3RhZmZOYW1lIjoiU3RhZmYgVGVuY2VudCIsInN0YWZmSWQiOiI5OTk5OTk5OSJ9.RfrkxoXJxZtsBEKseSpLzv1KGOTMgtj837uxSdL5ilZWjR0GoAb8TVS6Qrj7IPoS5uNmE901lq-shcZyKT6nvA";
        String key = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdGFmZk5hbWUiOiJTdGFmZiBUZW5jZW50IiwiZXhwIjoxNTkyNTM5MjcxLCJzdGFmZklkIjoiOTk5OTk5OTkifQ.uMYAO-vm_DP_FodC16DWx1FAH-V674wLFBztFZXFpcsv8rHpqFTJp-Ay-Jl9YqidSAd269e0hLtHRQxiGALRAg";
        Claims claims = tokenService.parseNotExpiredToken(token, key);
        System.out.println(claims);
    }
}
