function clip() {
   
    var clipText = window.clipboardData.getData('Text');

   
    clipRows = clipText.split(String.fromCharCode(13));

   
    for (i=0; i<clipRows.length; i++) {
        clipRows[i] = clipRows[i].split(String.fromCharCode(9));
    }

   
    newTable = document.getElementById("userTable");
    for (i=0; i<clipRows.length - 1; i++) {

        newRow = newTable.insertRow();

        for (j=0; j<clipRows[i].length; j++) {
                newCell = newRow.insertCell();   
                if(j==0){
                	newCell.innerHTML = '<input type="text" name="loginName" id="loginName" value="'+clipRows[i][j]+'">';
                }else if(j==1){
                	newCell.innerHTML = '<input type="text" name="userName" id="userName" value="'+clipRows[i][j]+'">';
                }else if(j==2){
                	newCell.innerHTML = '<input type="text" name="group" id="group">';
                }
        }
    }
}