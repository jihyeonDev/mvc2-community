var textarea = document.getElementById("textareaWrap");
var secretBtn = document.getElementById("secretBtn");
var secretBtnText = document.getElementById("secretBtnText");
var secretPost = document.getElementById("secretPost");
var secretValue = document.getElementById("secretValue");
var checked = false;

function resize(obj) {
    textarea.style.height = 'auto';
    obj.style.height = "1px";
    obj.style.height = (12+obj.scrollHeight)+"px";
}

secretPost.addEventListener('click', function() {
    checked = !checked;

    if(checked == true) { // 비밀글
        secretBtn.classList.add('on');
        secretBtnText.classList.add('on');
        secretValue.value = '1';
    } else { // 공개글
        secretBtn.classList.remove('on');
        secretBtnText.classList.remove('on');
        secretValue.value = '0';
    }
});

$(function () {
// 대댓글 모달창
    $("#commentBtn").click(function() {
        $("#modal").show();
        $("#modalDim").show();
    });

    $("#closeBtn").click(function (){
        $("#modal").hide();
        $("#modalDim").hide();
    });

    $("#modalDim").click(function (){
        $("#modal").hide();
        $("#modalDim").hide();
    });

// 파일 첨부
   $('#addFileBtn').click(function(e) {
      e.preventDefault();
      $('#file').click();
   });

// 썸네일 추가
    function setThumbnail(input) {
        if(input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('.image-preview').append('<img alt="첨부파일 미리보기" src="' + e.target.result + '"/>');
            }
            reader.readAsDataURL(input.files[0]);
        }
    }

// 썸네일 미리보기 스크롤바 토글
    function thumbnailScrollbar() {
        var imgs = $(".image-preview").children().size();
        if(imgs >= 2) {
            $(".image-preview").addClass("scrollbar");
        } else {
            $(".image-preview").removeClass("scrollbar");
        }
    }

    $("#file").change(function () {
        setThumbnail(this);
        thumbnailScrollbar();
    });

// textarea placeholder
    $('#content').click(function(){
        if($(this).val() == 'Vivers에 남겨보세요...'){
            $(this).val('');
        }
    });

    // var page = 15;
    // 스크롤 이벤트 - 무한 스크롤링, 헤더, 검색창 숨김
    $(document).scroll(function() {
        var maxHeight = $(document).height();
        var curentScroll = $(window).scrollTop() + $(window).height();

        if(curentScroll >= 960) {
            $('.left-aside').stop().animate({
                paddingTop: '100px'
            });
            $('.right-aside').stop().animate({
                paddingTop: '50px'
            });
            $('.top').stop().animate({
                height: '0'
            }, 10);
            $('.search').stop().animate({
                opacity: '0'
            }, 10);
        } else if (curentScroll <= 960) {
            $('.left-aside').stop().animate({
                paddingTop: '200px'
            });
            $('.right-aside').stop().animate({
                paddingTop: '150px'
            });
            $('.top').stop().animate({
                height: '34px'
            }, 10);
            $('.search').stop().animate({
                opacity: '1'
            }, 10);
        }
    });

});

