package URLTest;

import java.io.InputStream;
import java.net.URL;

import org.junit.Test;

public class TXTTest {

	@Test
	public void test() throws Exception {
		URL url  = new URL("https://bjbgp02.baidupcs.com/file/9b64e0a8b7fa7d9738074d1a86358c9c?bkt=p3-14009b64e0a8b7fa7d9738074d1a86358c9c3de9cd470000000022c2&fid=1228115943-250528-1095413589860096&time=1538489206&sign=FDTAXGERLQBHSKW-DCb740ccc5511e5e8fedcff06b081203-46uoiNYsuUQOuj7L6hOlANklkQI%3D&to=76&size=8898&sta_dx=8898&sta_cs=0&sta_ft=txt&sta_ct=7&sta_mt=7&fm2=MH%2CNanjing02%2CAnywhere%2C%2Chenan%2Cpbs&resv0=cdnback&resv1=0&vuk=1228115943&iv=0&htype=&newver=1&newfm=1&secfm=1&flow_ver=3&pkey=14009b64e0a8b7fa7d9738074d1a86358c9c3de9cd470000000022c2&sl=76480590&expires=8h&rt=pr&r=150897342&mlogid=6376202709677974922&vbdid=3994708231&fin=mysql%E6%95%B0%E6%8D%AE%E7%B1%BB%E5%9E%8B.txt&fn=mysql%E6%95%B0%E6%8D%AE%E7%B1%BB%E5%9E%8B.txt&rtype=1&dp-logid=6376202709677974922&dp-callid=0.1.1&hps=1&tsl=80&csl=80&csign=jMLhTCsU5VdTT6I918xYJtvbDuo%3D&so=0&ut=6&uter=4&serv=0&uc=2499153870&ti=26fa64dbec288224222a73d30eeaad47c8234ff1a32bd510305a5e1275657320&by=themis");
		InputStream in = url.openStream();
		byte[] txt = new byte[1024];
		int len = 0;
		while((len=in.read(txt))>0) {
			System.out.println(new String(txt));
		}
	}
}
