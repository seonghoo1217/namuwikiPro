function checkId(){

    var email=$("#username").val();

    let checkNum = -1;

    let num = 0;

    console.log(email)

    $.ajax({
        type:"get",
        url:'/api/loginuser/'+email+'/exist',
        dataType: "json"
    })
        .done(res=>{
            console.log(res, "중복 api 성공")
            if(res === 1){
                alert("사용할 수 있는 아이디입니다")
                num = 1;
                checkNum = 1;
            }else{
                alert("사용할 수 없는 아이디입니다")
                num = 0;
                checkNum = 2;
            }

        }).fail(error=>{
            console.log(error, "중복 api 오류")
    });
}