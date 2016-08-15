import org.boon.json.JsonFactory;
import org.boon.json.ObjectMapper;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;

import java.net.InetAddress;

import static spark.Spark.get;
import static spark.Spark.staticFileLocation;

public class App {
  protected static ObjectMapper mapper = JsonFactory.create();

  public static void main(final String[] args) throws Exception {
    final Client client = TransportClient
        .builder()
        .build()
        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
    staticFileLocation("/public");
    get("/", (request, response)-> new ModelAndView(null, "index.mustache"), new MustacheTemplateEngine());
  }
}
