//jshint esversion:6
function getDateToday() {
  const dateOptions = {
    weekday: "long",
    day: "numeric",
    month: "long"
  }
  const day = new Date().toLocaleDateString("en-US", dateOptions);
  return day;
}

module.exports.getDateToday = getDateToday;
