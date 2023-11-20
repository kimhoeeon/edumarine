var token = "IGQWRPUWZA4dVh4d1RaYlZAKVnJRbGpkZAWQxMDJMWGtiRFB4bUx4Q240UUF0cm5uU1paajlWRF9nWGhsLTVOSUtkbGNVTzNuTnFKbzJTY0otOXJDZA05nS3lQZAmdCU01OdWJsTTZAlNUxhMWlNVW5DcHFnTW4yYWQzcVUZD";


$.ajax({
    type: "GET",
    dataType: "jsonp",
    cache: false,
    url: "https://graph.instagram.com/me/media?access_token=" + token + "&fields=id,caption,media_type,media_url,thumbnail_url,permalink",
    success: function(response) {
        //console.log(response);
        if (response.data !== undefined && response.data.length > 0) {
            for(i = 0; i < 8; i++){
                if(response.data[i]){
                    var item = response.data[i];
                    var image_url = "";
                    var post = "";

                    if(item.media_type === "VIDEO"){
                        image_url = item.thumbnail_url;
                    }else{
                        image_url = item.media_url;
                    }

                    post += '<div class="instagram_item instagram_item'+i+'">';
                        post += '<a href="'+ item.permalink +'" target="_blank" rel="noopener noreferrer" style="background-image: url(' + image_url + ');">';
                            post += '<p>'+ item.caption +'</p>';
                        post += '</a>';
                    post += '</div>';

                    $('#instagram').append(post);
                }else{
                    // if no curent item
                    show_fallback('#insta-item-'+i)
                }
            }
        }else{
            // if api error
            show_fallback('.instagram-item')
        }
    },
    error :function(){
        // if http error
        show_fallback('.instagram-item')
    }

});

function show_fallback(el){
    $(el).addClass('loaded fallback');
}