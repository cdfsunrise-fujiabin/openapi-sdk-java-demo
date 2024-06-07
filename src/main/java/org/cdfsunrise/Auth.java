package org.cdfsunrise;

import org.cdfsunrise.open.V2UserAuth;
import org.cdfsunrise.service.RsaService;

public class Auth {
    public static String getToken() {
        var test = new V2UserAuth();
        try {
            // TODO 这里替换成由中免日上提供的测试服务器地址
            String host = "";

            // TODO 这里替换成由中免日上提供的appid
            String appId = "";

            // TODO 这里替换成由中免日上提供的appPwd
            String appPwd = "";

            // TODO 这里替换成由中免日上提供的rsa公钥
            String rsaPubKey = """
-----BEGIN RSA PUBLIC KEY-----
MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0XAbluGzEsRhZ82X20bI
vUPxtTLiBJQiDfJtpLF4YjmxB04cU9LHAIGsxnK/VDcnlIlOutneJarxWwVq5sEe
Vp9bMG+KtNPPdXFj+tMb4hloKwFAVXiyxLoDrgRW5DDes8MUI+6kiIGX4hi5KSRP
mKFVtMOLD142+kuRaCEYvz4gz85cRiOa09jJ8JEvU+8DieysJJrEvVVaexGjJD3o
hFslRLfoG06NbvaSwdqL1+z98pdp4JMjx47BccUTEXB1jVVOmU3zyNVP33v4iVyW
q5O47ccKVMuvxN5dlGsXEOoSuesWxQblF2TjNt1Vd8D73l7por4Gm7Gf4Mw05Tyl
JwIDAQAB
-----END RSA PUBLIC KEY-----
""";

            var req = new V2UserAuth.OpenAuthReq();
            req.setAppid(appId);
            req.setPassword(RsaService.encrypt(appPwd, rsaPubKey));
            V2UserAuth.V2UserAuthResponse response = test.V2UserAuth(host, req);
            String data = response.GetData();
            return data;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
