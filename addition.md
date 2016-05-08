Slogo Addition
=======
###Estimation
How long do you think it will take you to complete this new feature?
* I think it will take around 2 hours to complete this new feature. I think the pieces are already there, just need to fit them all together.
How many files will you need to add or update? Why?
* Probably 3 or 4. Will definitely need to update Movement to account for it. Might need to modify Turtle Properties to keep in the Turtle Display dimensions. Will also have to add the command classes.

###Review
How long did it take you to complete this new feature?
* Took around 2 hours.
How many files did you need to add or update? Why?
* Updated Movement to stop it from moving past the border.
* Updated commandLocations.properties to add in the commands.
* Added the two commands.
* Updated TurtleProperties to account for this.
* Hardcoded commands into SlogoParaser. For some reason, it didn't work when I just put them in English.properties.
* Finally, updated the display size in Slogo
Took a few more things, ran especially into trouble with the display not being the right size. They had made it gigantic so that it just continues on.

Did you get it completely right on the first try?
* I got the mechanics right, but it was buggy. First it didn't even recognize the commands, so I hard coded them in slogo parser. Next, the screen was wrongly sized, so it would go on forever. So I changed the size of the display in Slogo.java. Then it worked.

### Analysis: what do you feel this exercise reveals about your project's design and documentation?
was it as good (or bad) as you remembered?
* Yep, it was pretty good. Wasn't too hard to implement.
what could be improved?
* As stated in my Slogo analysis, the Movement class contains too much frontend and should not be in the backend. This was the biggest issue with our design.
what would it have been like if you were not familiar with the code at all?
what would it have been like if you were not familiar with the code at all?
* I don't think adding the mechanics would have been that bad. The biggest issue was the not parsing correctly. I don't think someone else would have been easily able to fix it because it's such a nitty gritty thing. 
