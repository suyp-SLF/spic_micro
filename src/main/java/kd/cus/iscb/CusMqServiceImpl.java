package kd.cus.iscb;

import kd.cus.iscb.service.CusMqService;

import java.util.Map;

public class CusMqServiceImpl implements CusMqService {
    @Override
    public Map<String, Object> test(String str) {

        System.out.println(str);
        return null;
    }
}
