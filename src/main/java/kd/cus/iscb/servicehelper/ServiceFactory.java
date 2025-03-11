package kd.cus.iscb.servicehelper;

import kd.bos.dataentity.TypesContainer;
import kd.bos.dataentity.resource.ResManager;
import kd.bos.instance.Instance;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Wu Yanqi
 */
public class ServiceFactory {
    private static Map<String, String> serviceMap = new HashMap();

    public ServiceFactory() {
    }

    public static void putService(String serviceName, String serviceImpl) {
        serviceMap.put(serviceName, serviceImpl);
    }

    public static Object getService(String serviceName) {
        String className = (String) serviceMap.get(serviceName);
        if (className == null) {
            String appName = Instance.getAppName();
            throw new RuntimeException(String.format(ResManager.loadKDString("%s对应的服务实现在%s未找到", "ServiceFactory_0", "bos-entity-core", new Object[0]), serviceName, appName));
        } else {
            return TypesContainer.getOrRegisterSingletonInstance(className);
        }
    }

    static {
        serviceMap.put("MddService", "kd.cus.iscb.MddServiceImpl");
        serviceMap.put("VoucherDataService", "kd.cus.iscb.VoucherDataServiceImp");
        serviceMap.put("CusMqService","kd.cus.iscb.CusMqServiceImpl");
    }
}
