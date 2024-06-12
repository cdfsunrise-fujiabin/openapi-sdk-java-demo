package org.cdfsunrise;

import org.cdfsunrise.service.RsaService;
import org.cdfsunrise.service.SignService;

import java.util.SortedMap;
import java.util.TreeMap;

public class V1QueryGoodsStock {
    public static String DoQuery() {
        org.cdfsunrise.open.V1QueryGoodsStock service = new org.cdfsunrise.open.V1QueryGoodsStock();
        try {
            org.cdfsunrise.open.V1QueryGoodsStock.OpenDataReq req = new org.cdfsunrise.open.V1QueryGoodsStock.OpenDataReq();
            // TODO：查询结构体为：{"extGoodsId":"","warehouse":"","merchantId":"","lefoxId":""}， 属性参数按需填充即可
            // TODO: rsa public key也应填充为自己账号中的rsa public key
            req.setData(RsaService.encrypt("", "-----BEGIN RSA PUBLIC KEY-----\n" +
                    "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0XAbluGzEsRhZ82X20bI\n" +
                    "vUPxtTLiBJQiDfJtpLF4YjmxB04cU9LHAIGsxnK/VDcnlIlOutneJarxWwVq5sEe\n" +
                    "Vp9bMG+KtNPPdXFj+tMb4hloKwFAVXiyxLoDrgRW5DDes8MUI+6kiIGX4hi5KSRP\n" +
                    "mKFVtMOLD142+kuRaCEYvz4gz85cRiOa09jJ8JEvU+8DieysJJrEvVVaexGjJD3o\n" +
                    "hFslRLfoG06NbvaSwdqL1+z98pdp4JMjx47BccUTEXB1jVVOmU3zyNVP33v4iVyW\n" +
                    "q5O47ccKVMuvxN5dlGsXEOoSuesWxQblF2TjNt1Vd8D73l7por4Gm7Gf4Mw05Tyl\n" +
                    "JwIDAQAB\n" +
                    "-----END RSA PUBLIC KEY-----"));
            req.setAppid("");
            req.setDataEncryptMethod("rsa"); // 这个目前固定，不用改
            req.setSignEncryptMethod("md5"); // 这个目前固定，不用改
            req.setTimestamp(SignService.GetCurrentTimestampSeconds()); // 这个目前固定，不用改

            SortedMap<String, String> sortedMap = new TreeMap<>();
            sortedMap.put("key", ""); // TODO：这个为中免日上注册的商户账号中的SignKey字段，按需填充
            sortedMap.put("timestamp", req.getTimestamp());
            sortedMap.put("data", req.getData());
            sortedMap.put("appid", req.getAppid());
            sortedMap.put("dataEncryptMethod", req.getDataEncryptMethod());
            sortedMap.put("signEncryptMethod", req.getSignEncryptMethod());

            req.setSign(SignService.Sign(sortedMap));
            // TODO: host为请求的环境域名，详见文档中章节：各环境域名
            // TODO: authToken 通过示例的Auth -> getToken 获取
            org.cdfsunrise.open.V1QueryGoodsStock.V1QueryGoodsStockResponse response = service.V1QueryGoodsStock("", "", req);
            String data = response.GetData();
            return data;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
