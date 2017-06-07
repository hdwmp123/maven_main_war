import java.io.File;
import java.net.URL;
import java.security.ProtectionDomain;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class CompareLuncher {
    public static void main(String[] args) throws Exception {
        String currentPath = new File("").getAbsolutePath();
        // 如果没有work目录，则创建,jetty默认解压路径
        File work = new File(currentPath + "\\work");
        if (!work.exists()) {
            work.mkdir();
        }
        Server server = null;
        Integer port = 8090;
        server = new Server(port);
        ProtectionDomain domain = CompareLuncher.class.getProtectionDomain();
        URL location = domain.getCodeSource().getLocation();
        WebAppContext webapp = new WebAppContext();
        webapp.setContextPath("/");
        webapp.setWar(location.toExternalForm());
        server.setHandler(webapp);
        server.start();
        server.join();

        // 启动部署包的时候可以用这个
        // // Server server = new Server(8080);
        // // WebAppContext context = new WebAppContext();
        // // context.setContextPath("/compare");
        // // context.setWar("F:/compare.war");
        // // server.setHandler(context);
        // // server.start();
        // // server.join();

        // eclipse 测试的时候启用如下代码,debug模式下可以直接看到修改效果
        // Server server = new Server(8090);
        // //
        // ResourceHandler resourceHandler = new ResourceHandler();
        // resourceHandler.setDirectoriesListed(true);
        //
        // server.setSendServerVersion(true);
        // server.setStopAtShutdown(true);
        // server.setHandler(getWebAppContext());
        // server.start();
        // server.join();

    }

    private static WebAppContext getWebAppContext() {

        String path = CompareLuncher.class.getResource("/").getFile()
                .replaceAll("/target/(.*)", "")
                + "/src/main/webapp";
        // System.out.println(path);
        String path2 = new File("").getAbsolutePath() + "\\src\\main\\webapp";
        // System.out.println();

        return new WebAppContext(path2, "/");
    }
}
