INSERT INTO USERS (NAME,CITY,STATE,ZIPCODE,HAS_PEANUT_ALLERGY,HAS_EGG_ALLERGY,HAS_DAIRY_ALLERGY)
VALUES ('FirstUser','CITY','STATE',12345,true,false,false);
INSERT INTO USERS (NAME,CITY,STATE,ZIPCODE,HAS_PEANUT_ALLERGY,HAS_EGG_ALLERGY,HAS_DAIRY_ALLERGY)
VALUES ('SecondUser','CITY','STATE',12345,true,false,false);

INSERT INTO RESTAURANTS (NAME)
VALUES ('FirstRestaurant');
INSERT INTO RESTAURANTS (NAME)
VALUES ('SecondRestaurant');

INSERT INTO REVIEWS (USER_NAME,RESTAURANT_ID,PEANUT_SCORE,EGG_SCORE,DAIRY_SCORE)
VALUES ('FirstUser',1,2.5,2.5,2.5);
INSERT INTO REVIEWS (USER_NAME,RESTAURANT_ID,PEANUT_SCORE,EGG_SCORE,DAIRY_SCORE)
VALUES ('SecondUser',2,2.5,2.5,2.5);
