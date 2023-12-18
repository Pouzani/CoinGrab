function submitDownVote() {
  document.getElementById("percentageFormDown").submit();
  modalDown.style.display = "none";
}

function submitUpVote() {
  document.getElementById("percentageFormUp").submit();
  modalDown.style.display = "none";
}

function submitSearch() {
  document.getElementById("searchForm").submit();
}

function submitEdit(){
  document.getElementById("editForm").submit();
}
