# hackathon2016
Add following configs in elasticsearch/config/elasticsearch.yml
```YAML
http.cors.enabled : true

http.cors.allow-origin : "*"
http.cors.allow-methods : OPTIONS, HEAD, GET, POST, PUT, DELETE
http.cors.allow-headers : X-Requested-With,X-Auth-Token,Content-Type, Content-Length

http.jsonp.enable: true
```

The Shakespeare data set is organized in the following schema:
```json
{
    "line_id": INT,
    "play_name": "String",
    "speech_number": INT,
    "line_number": "String",
    "speaker": "String",
    "text_entry": "String",
}
```
Use the following command to set up a mapping for the Shakespeare data set:
```shell
curl -XPUT http://localhost:9200/shakespeare -d '
{
 "mappings" : {
  "_default_" : {
   "properties" : {
    "speaker" : {"type": "string", "index" : "not_analyzed" },
    "play_name" : {"type": "string", "index" : "not_analyzed" },
    "line_id" : { "type" : "integer" },
    "speech_number" : { "type" : "integer" }
   }
  }
 }
}
';
```
Use the Elasticsearch bulk API to load the data sets with the following commands:
```shell
curl -XPOST 'localhost:9200/shakespeare/_bulk?pretty' --data-binary @shakespeare.json
```
Load with the following command:
```shell
curl 'localhost:9200/_cat/indices?v'
```

Delete index:
```shell
curl -XDELETE 'http://localhost:9200/shakespeare/'
```