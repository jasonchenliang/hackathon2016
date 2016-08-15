# hackathon2016
Add following configs in elasticsearch/config/elasticsearch.yml
```YAML
http.cors.enabled : true

http.cors.allow-origin : "*"
http.cors.allow-methods : OPTIONS, HEAD, GET, POST, PUT, DELETE
http.cors.allow-headers : X-Requested-With,X-Auth-Token,Content-Type, Content-Length

http.jsonp.enable: true
```