package pl.project13.meetupgetnames.data

/*
{
onDay: "5.15.2013",
startsAt: "10:20",
inRoom: "1",
roomName: "Room 1",
speaker: "Patrick Copeland",
topic: "Pretotyping"
},
 */
case class NextUpSession(
  onDay: String,
  startsAt: String,
  inRoom: String,
  roomName: String,
  speaker: String,
  topic: String
)