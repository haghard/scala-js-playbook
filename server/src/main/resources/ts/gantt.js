
var columns = []
var rows = [[]]

function setColumns(d) {
    columns = d
}

function setRows(d) {
    rows = d
}

function init() {
  google.charts.load('current', {'packages':['gantt'], callback: onload });
  google.charts.setOnLoadCallback(draw);
}

function onload() {
  console.log("loaded")
}

function draw() {

  var dt = new google.visualization.DataTable();
  for (var i in columns) {
    dt.addColumn(columns[i].f, columns[i].s);
  }

  for (var i in rows) {
    console.log(rows[i]);
    dt.addRow(rows[i]);
  }

  //dt.addRows(rows);

  /*dt.addRows([
    ['toTrain', 'Walk to train stop', 'walk', null, null, toMilliseconds(5), 100, null],
    ['music', 'Listen to music', 'music', null, null, toMilliseconds(70), 100, null],
    ['wait', 'Wait for train', 'wait', null, null, toMilliseconds(10), 100, 'toTrain'],
    ['train', 'Train ride', 'train', null, null, toMilliseconds(45), 75, 'wait'],
    ['toWork', 'Walk to work', 'walk', null, null, toMilliseconds(10), 0, 'train'],
    ['work', 'Sit down at desk', null, null, null, toMilliseconds(2), 0, 'toWork'],
  ]);*/

  var options = {
    height: 275,
    gantt: {
      defaultStartDateMillis: new Date(2015, 3, 28)
    }
  };

  var chart = new google.visualization.Gantt(document.getElementById('gnt'));
  chart.draw(dt, options);
}

function toMilliseconds(minutes) {
   return minutes * 60 * 1000;
}