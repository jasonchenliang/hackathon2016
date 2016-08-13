import org.boon.json.JsonFactory;
import org.boon.json.ObjectMapper;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;

import java.net.InetAddress;

import static spark.Spark.get;

public class App {
  protected static ObjectMapper mapper = JsonFactory.create();

  public static void main(final String[] args) throws Exception {
    final Client client = TransportClient
        .builder()
        .build()
        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));

    get("/", (request, response)-> new ModelAndView(null, "index.mustache"), new MustacheTemplateEngine());

//    get("/", (request, response) -> {
//      final SearchResponse searchResponse = client.prepareSearch("music").setTypes("lyrics").execute().actionGet();
//      final SearchHit[] hits = searchResponse.getHits().getHits();
//
//      final Map<String, Object> attributes = new HashMap<>();
//      attributes.put("songs", hits);
//
//      return new ModelAndView(attributes, "index.mustache");
//    }, new MustacheTemplateEngine());
//
//
//    get("/search", (request, response) -> {
//      final SearchRequestBuilder srb = client.prepareSearch("music").setTypes("lyrics");
//
//      final String lyricParam = request.queryParams("query");
//      QueryBuilder lyricQuery = null;
//      if (lyricParam != null && !lyricParam.trim().isEmpty()) {
//        lyricQuery = QueryBuilders.matchQuery("lyrics",
//                                              lyricParam);
//        // srb.setQuery(qb).addHighlightedField("lyrics", 0, 0);
//      }
//      final String artistParam = request.queryParams("artist");
//      QueryBuilder artistQuery = null;
//      if (artistParam != null && !artistParam.trim().isEmpty()) {
//        artistQuery = QueryBuilders.matchQuery("artist", artistParam);
//      }
//
//      if (lyricQuery != null && artistQuery == null) {
//        srb.setQuery(lyricQuery).addHighlightedField("lyrics", 0, 0);
//      }
//      else if (lyricQuery == null && artistQuery != null) {
//        srb.setQuery(artistQuery);
//      }
//      else if (lyricQuery != null && artistQuery != null) {
//        srb.setQuery(QueryBuilders.andQuery(artistQuery, lyricQuery)).addHighlightedField("lyrics", 0, 0);
//      }
//
//      final SearchResponse searchResponse = srb.execute().actionGet();
//
//      final SearchHit[] hits = searchResponse.getHits().getHits();
//
//      final Map<String, Object> attributes = new HashMap<>();
//      attributes.put("songs", hits);
//
//      return new ModelAndView(attributes, "index.mustache");
//    }, new MustacheTemplateEngine());
//
//
//    get("/add", (request, response) -> new ModelAndView(new HashMap(), "add.mustache"), new MustacheTemplateEngine());
//
//
//    post("/save", (request, response) -> {
//      final String json = "{" + "\"name\":\"" + request.raw().getParameter("name") + "\"," +
//          "\"artist\":\"" + request.raw().getParameter("artist") + "\"," +
//          "\"year\":" + request.raw().getParameter("year") + "," +
//          "\"album\":\"" + request.raw().getParameter("album") + "\"," +
//          "\"lyrics\":\"" + request.raw().getParameter("lyrics") + "\"}";
//
//      final IndexRequest indexRequest = new IndexRequest("music", "lyrics", UUID.randomUUID().toString());
//      indexRequest.source(json);
//      final IndexResponse esResponse = client.index(indexRequest).actionGet();
//
//      final Map<String, Object> attributes = new HashMap<>();
//      return new ModelAndView(attributes, "index.mustache");
//    }, new MustacheTemplateEngine());
  }
}
