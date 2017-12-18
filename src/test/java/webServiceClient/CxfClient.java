package webServiceClient;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.xml.namespace.QName;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.junit.Test;


/**
 * Webservice 客户端测试
 */
public class CxfClient {

	@SuppressWarnings("unchecked")
	private static <E> E callService(String method, Map<String, String> params,
									 String url, String nameSpace) {
		try {
			JaxWsDynamicClientFactory clientFactory = JaxWsDynamicClientFactory.newInstance();
			Client client = clientFactory.createClient(url);
			QName opName = new QName(nameSpace, method);
			Object[] ps = new Object[params.size()];
			int i = 0;
			for (Entry<String, String> param : params.entrySet()) {
				ps[i] = param.getValue();
				i++;
			}
			E[] strs = (E[]) client.invoke(opName, ps);
			return strs[0];
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
    @Test
	public void test() {
        String nameSpace = "http://webService.myDemo.com/";
		String url = "http://localhost:8082/webservice/WebserviceTest?wsdl";
		Map<String, String> params = new LinkedHashMap<String, String>();
		//参数设置
		params.put("name","jim");
		String callService = callService("sayHello", params, url,nameSpace);
		System.out.println(callService);
	}

}
