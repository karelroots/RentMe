$(function(){
    $('img.avatar').each(function(){
        var this_image = this;
        var src = $(this_image).attr('src');
        var lsrc = $(this_image).attr('data-src');
        if(lsrc.length > 0){
            var img = new Image();
            $(img).on("load", function() {
                this_image.src = this.src;
            });
            img.src = lsrc;
        }else{
            this_image.src = src;
        }
    });
});