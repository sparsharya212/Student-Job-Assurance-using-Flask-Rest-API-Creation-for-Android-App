Basic Android Application built to predict if an aspiring student will be able to crack a job interview based on 3 vector attributes: CGPA, IQ and score. The Model uses random Forest algorithm to produce 83% percent accuracy. This file is serialized and imported into flask. This file is then deployed on a live server using heroku.

An android App is built to take in 3 parameters in the form of a Hashmap. These parameters are passed onto request que for feedback from the model. Based on the JSON output, "Student Will Crack the JOB!!"/ "Student Needs to Work More Hard!!" will be displayed onto the main activity of the android application.
