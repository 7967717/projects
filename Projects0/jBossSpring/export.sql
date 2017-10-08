--------------------------------------------------------
--  File created - Friday-February-14-2014   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Sequence ARTICLES_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "DOCSTORE"."ARTICLES_SEQ"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 141 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence ARTICLE_TYPE_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "DOCSTORE"."ARTICLE_TYPE_SEQ"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence FILE_TYPES_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "DOCSTORE"."FILE_TYPES_SEQ"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence LANGUAGES_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "DOCSTORE"."LANGUAGES_SEQ"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence SHARED_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "DOCSTORE"."SHARED_SEQ"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence SHARED_TYPE_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "DOCSTORE"."SHARED_TYPE_SEQ"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence USERS_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "DOCSTORE"."USERS_SEQ"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence USER_ROLES_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "DOCSTORE"."USER_ROLES_SEQ"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Table ARTICLES
--------------------------------------------------------

  CREATE TABLE "DOCSTORE"."ARTICLES" 
   (	"ID" NUMBER, 
	"AUTHOR" VARCHAR2(4000 BYTE), 
	"SUBJECT" VARCHAR2(4000 BYTE), 
	"STATUS" VARCHAR2(11 BYTE), 
	"TITLE" VARCHAR2(4000 BYTE), 
	"CREATED" DATE DEFAULT SYSDATE, 
	"FILE_NAME" VARCHAR2(4000 BYTE), 
	"MODIFIED" TIMESTAMP (6) DEFAULT CURRENT_TIMESTAMP, 
	"FILE_SIZE" NUMBER, 
	"PATH" VARCHAR2(4000 BYTE), 
	"MD5" VARCHAR2(4000 BYTE), 
	"ID_OWNER" NUMBER, 
	"ID_LANG" NUMBER, 
	"KEYWORDS" VARCHAR2(4000 BYTE), 
	"PAGE_SIZE" VARCHAR2(100 BYTE), 
	"ID_SHARED_TYPE" NUMBER, 
	"NUMBER_OF_PAGES" NUMBER, 
	"FILE_TYPE" VARCHAR2(10 BYTE), 
	"ID_ARTICLE_TYPE" NUMBER
   )
--------------------------------------------------------
--  DDL for Table ARTICLE_TYPE
--------------------------------------------------------

  CREATE TABLE "DOCSTORE"."ARTICLE_TYPE" 
   (	"TYPE_ID" NUMBER(3,0), 
	"TYPE" VARCHAR2(50 BYTE)
   )

--------------------------------------------------------
--  DDL for Table LANGUAGES
--------------------------------------------------------

  CREATE TABLE "DOCSTORE"."LANGUAGES" 
   (	"ID_LANG" NUMBER, 
	"LANGUAGE" VARCHAR2(20 BYTE)
   )
--------------------------------------------------------
--  DDL for Table SHARED
--------------------------------------------------------

  CREATE TABLE "DOCSTORE"."SHARED" 
   (	"ID_SHARED" NUMBER, 
	"ID" NUMBER, 
	"ID_OWNER" NUMBER, 
	"ID_GRANTEE" NUMBER
   )
--------------------------------------------------------
--  DDL for Table SHARED_TYPE
--------------------------------------------------------

  CREATE TABLE "DOCSTORE"."SHARED_TYPE" 
   (	"ID_SHARED_TYPE" NUMBER(1,0), 
	"SHARED_TYPE" VARCHAR2(7 BYTE)
   )
--------------------------------------------------------
--  DDL for Table USERS
--------------------------------------------------------

  CREATE TABLE "DOCSTORE"."USERS" 
   (	"USER_ID" NUMBER(6,0), 
	"USERNAME" VARCHAR2(45 BYTE), 
	"PASSWORD" VARCHAR2(45 BYTE), 
	"ENABLED" NUMBER(1,0)
   ) ;
--------------------------------------------------------
--  DDL for Table USER_ROLES
--------------------------------------------------------

  CREATE TABLE "DOCSTORE"."USER_ROLES" 
   (	"USER_ROLE_ID" NUMBER(6,0), 
	"USER_ID" NUMBER(6,0), 
	"AUTHORITY" VARCHAR2(45 BYTE)
   )
REM INSERTING into DOCSTORE.ARTICLES
SET DEFINE OFF;
Insert into DOCSTORE.ARTICLES (ID,AUTHOR,SUBJECT,STATUS,TITLE,CREATED,FILE_NAME,MODIFIED,FILE_SIZE,PATH,MD5,ID_OWNER,ID_LANG,KEYWORDS,PAGE_SIZE,ID_SHARED_TYPE,NUMBER_OF_PAGES,FILE_TYPE,ID_ARTICLE_TYPE) values (126,'ugur','! - no data - !','NON_INDEXED','Microsoft Word - CIDR07Keynote.doc',to_date('12-FEB-14','DD-MON-RR'),'One Size Fits All �?? Part 2 Benchmarking Results.pdf',to_timestamp('12-FEB-14 09.52.57.058000000 AM','DD-MON-RR HH.MI.SSXFF AM'),213989,'/STORAGE/MAIN_CATALOGUE/126.pdf','1355746470',2,1,'! - no data - !','Rectangle: 612.0x792.0 (rot: 0 degrees)',1,12,'.pdf',1);
Insert into DOCSTORE.ARTICLES (ID,AUTHOR,SUBJECT,STATUS,TITLE,CREATED,FILE_NAME,MODIFIED,FILE_SIZE,PATH,MD5,ID_OWNER,ID_LANG,KEYWORDS,PAGE_SIZE,ID_SHARED_TYPE,NUMBER_OF_PAGES,FILE_TYPE,ID_ARTICLE_TYPE) values (127,'! - no data - !','! - no data - !','NON_INDEXED','Sun Certified Enterprise Architect for Java EE Study Guide, 2nd Edition (2010) (ATTiCA)',to_date('12-FEB-14','DD-MON-RR'),'Cade, Sheil. Sun Certified Enterprise Architect for Java EE Study Guide. 2 ed. EN (ver 1).pdf',to_timestamp('12-FEB-14 11.37.43.260000000 AM','DD-MON-RR HH.MI.SSXFF AM'),2343434,'/STORAGE/MAIN_CATALOGUE/127.pdf','1615565949',2,1,null,'Rectangle: 252.0x334.8 (rot: 0 degrees)',1,213,'.pdf',1);
Insert into DOCSTORE.ARTICLES (ID,AUTHOR,SUBJECT,STATUS,TITLE,CREATED,FILE_NAME,MODIFIED,FILE_SIZE,PATH,MD5,ID_OWNER,ID_LANG,KEYWORDS,PAGE_SIZE,ID_SHARED_TYPE,NUMBER_OF_PAGES,FILE_TYPE,ID_ARTICLE_TYPE) values (128,'! - no data - !','! - no data - !','NON_INDEXED','! - no data - !',to_date('12-FEB-14','DD-MON-RR'),'Effective.Java.2nd.Edition.May.2008.3000th.Release.pdf',to_timestamp('12-FEB-14 11.39.33.153000000 AM','DD-MON-RR HH.MI.SSXFF AM'),1869211,'/STORAGE/MAIN_CATALOGUE/128.pdf','1674638377',2,1,'! - no data - !','Rectangle: 252.0x317.16 (rot: 0 degrees)',1,369,'.pdf',1);
Insert into DOCSTORE.ARTICLES (ID,AUTHOR,SUBJECT,STATUS,TITLE,CREATED,FILE_NAME,MODIFIED,FILE_SIZE,PATH,MD5,ID_OWNER,ID_LANG,KEYWORDS,PAGE_SIZE,ID_SHARED_TYPE,NUMBER_OF_PAGES,FILE_TYPE,ID_ARTICLE_TYPE) values (129,'Bruno Lowagie','This example shows how to add metadata','NON_INDEXED','Hello World example',to_date('12-FEB-14','DD-MON-RR'),'pdf_metadata.pdf',to_timestamp('12-FEB-14 03.44.55.486000000 PM','DD-MON-RR HH.MI.SSXFF AM'),1074,'/STORAGE/MAIN_CATALOGUE/129.pdf','27189144',2,null,'Metadata, iText, PDF','Rectangle: 595.0x842.0 (rot: 0 degrees)',1,1,'.pdf',null);
Insert into DOCSTORE.ARTICLES (ID,AUTHOR,SUBJECT,STATUS,TITLE,CREATED,FILE_NAME,MODIFIED,FILE_SIZE,PATH,MD5,ID_OWNER,ID_LANG,KEYWORDS,PAGE_SIZE,ID_SHARED_TYPE,NUMBER_OF_PAGES,FILE_TYPE,ID_ARTICLE_TYPE) values (130,'Administrator','NO_INFO','NON_INDEXED','Microsoft Word - Core J2EE Patterns.doc',to_date('12-FEB-14','DD-MON-RR'),'Sun Core J2EE Patterns. 1ed. EN.pdf',to_timestamp('12-FEB-14 06.06.13.896000000 PM','DD-MON-RR HH.MI.SSXFF AM'),3249483,'/STORAGE/MAIN_CATALOGUE/130.pdf','2045970494',2,1,'NO_INFO','Rectangle: 596.0x842.0 (rot: 0 degrees)',1,420,'.pdf',1);
Insert into DOCSTORE.ARTICLES (ID,AUTHOR,SUBJECT,STATUS,TITLE,CREATED,FILE_NAME,MODIFIED,FILE_SIZE,PATH,MD5,ID_OWNER,ID_LANG,KEYWORDS,PAGE_SIZE,ID_SHARED_TYPE,NUMBER_OF_PAGES,FILE_TYPE,ID_ARTICLE_TYPE) values (131,'Craig Walls','NO_INFO','NON_INDEXED','Spring in Action, Third Edition',to_date('13-FEB-14','DD-MON-RR'),'Walls C. - Spring in Action. Third Edition - 2011.pdf',to_timestamp('13-FEB-14 10.01.18.711000000 AM','DD-MON-RR HH.MI.SSXFF AM'),10521957,'/STORAGE/MAIN_CATALOGUE/131.pdf','552813888',2,1,'NO_INFO','Rectangle: 531.0x666.0 (rot: 0 degrees)',1,426,'.pdf',1);
Insert into DOCSTORE.ARTICLES (ID,AUTHOR,SUBJECT,STATUS,TITLE,CREATED,FILE_NAME,MODIFIED,FILE_SIZE,PATH,MD5,ID_OWNER,ID_LANG,KEYWORDS,PAGE_SIZE,ID_SHARED_TYPE,NUMBER_OF_PAGES,FILE_TYPE,ID_ARTICLE_TYPE) values (132,'NO_INFO','NO_INFO','NON_INDEXED','productive_javaee5',to_date('13-FEB-14','DD-MON-RR'),'Real World Java EE Patterns.pdf',to_timestamp('13-FEB-14 10.01.32.041000000 AM','DD-MON-RR HH.MI.SSXFF AM'),6684000,'/STORAGE/MAIN_CATALOGUE/132.pdf','42734362',2,1,'NO_INFO','Rectangle: 536.0x697.0 (rot: 0 degrees)',1,279,'.pdf',1);
Insert into DOCSTORE.ARTICLES (ID,AUTHOR,SUBJECT,STATUS,TITLE,CREATED,FILE_NAME,MODIFIED,FILE_SIZE,PATH,MD5,ID_OWNER,ID_LANG,KEYWORDS,PAGE_SIZE,ID_SHARED_TYPE,NUMBER_OF_PAGES,FILE_TYPE,ID_ARTICLE_TYPE) values (133,null,null,'NON_INDEXED','0137061501.pdf',to_date('13-FEB-14','DD-MON-RR'),'Cade, Sheil. Sun Certified Enterprise Architect for Java EE Study Guide. 2 ed. EN (ver 2).pdf',to_timestamp('13-FEB-14 10.12.19.964000000 AM','DD-MON-RR HH.MI.SSXFF AM'),2384144,'/STORAGE/MAIN_CATALOGUE/133.pdf','897654386',2,1,null,'Rectangle: 504.0x656.5 (rot: 0 degrees)',1,212,'.pdf',1);
Insert into DOCSTORE.ARTICLES (ID,AUTHOR,SUBJECT,STATUS,TITLE,CREATED,FILE_NAME,MODIFIED,FILE_SIZE,PATH,MD5,ID_OWNER,ID_LANG,KEYWORDS,PAGE_SIZE,ID_SHARED_TYPE,NUMBER_OF_PAGES,FILE_TYPE,ID_ARTICLE_TYPE) values (134,'Administrator','!-no info-!','NON_INDEXED','Microsoft Word - Core J2EE Patterns.doc',to_date('13-FEB-14','DD-MON-RR'),'Sun Core J2EE Patterns. 1ed. EN.pdf',to_timestamp('13-FEB-14 10.12.34.207000000 AM','DD-MON-RR HH.MI.SSXFF AM'),3249483,'/STORAGE/MAIN_CATALOGUE/134.pdf','107016695',2,1,'!-no info-!','Rectangle: 596.0x842.0 (rot: 0 degrees)',1,420,'.pdf',1);
Insert into DOCSTORE.ARTICLES (ID,AUTHOR,SUBJECT,STATUS,TITLE,CREATED,FILE_NAME,MODIFIED,FILE_SIZE,PATH,MD5,ID_OWNER,ID_LANG,KEYWORDS,PAGE_SIZE,ID_SHARED_TYPE,NUMBER_OF_PAGES,FILE_TYPE,ID_ARTICLE_TYPE) values (135,'Craig Walls','!-no info-!','NON_INDEXED','Spring in Action, Third Edition',to_date('13-FEB-14','DD-MON-RR'),'Walls C. - Spring in Action. Third Edition - 2011.pdf',to_timestamp('13-FEB-14 10.15.45.115000000 AM','DD-MON-RR HH.MI.SSXFF AM'),10521957,'/STORAGE/MAIN_CATALOGUE/135.pdf','727792240',2,1,'!-no info-!','Rectangle: 531.0x666.0 (rot: 0 degrees)',1,426,'.pdf',1);
Insert into DOCSTORE.ARTICLES (ID,AUTHOR,SUBJECT,STATUS,TITLE,CREATED,FILE_NAME,MODIFIED,FILE_SIZE,PATH,MD5,ID_OWNER,ID_LANG,KEYWORDS,PAGE_SIZE,ID_SHARED_TYPE,NUMBER_OF_PAGES,FILE_TYPE,ID_ARTICLE_TYPE) values (136,'Administrator','!-no info-!','NON_INDEXED','Microsoft Word - Core J2EE Patterns.doc',to_date('13-FEB-14','DD-MON-RR'),'Sun Core J2EE Patterns. 1ed. EN.pdf',to_timestamp('13-FEB-14 10.16.42.435000000 AM','DD-MON-RR HH.MI.SSXFF AM'),3249483,'/STORAGE/MAIN_CATALOGUE/136.pdf','391348187',2,1,'!-no info-!','Rectangle: 596.0x842.0 (rot: 0 degrees)',1,420,'.pdf',1);
Insert into DOCSTORE.ARTICLES (ID,AUTHOR,SUBJECT,STATUS,TITLE,CREATED,FILE_NAME,MODIFIED,FILE_SIZE,PATH,MD5,ID_OWNER,ID_LANG,KEYWORDS,PAGE_SIZE,ID_SHARED_TYPE,NUMBER_OF_PAGES,FILE_TYPE,ID_ARTICLE_TYPE) values (137,'!-no info-!','!-no info-!','NON_INDEXED','productive_javaee5',to_date('13-FEB-14','DD-MON-RR'),'Real World Java EE Patterns.pdf',to_timestamp('13-FEB-14 10.16.53.796000000 AM','DD-MON-RR HH.MI.SSXFF AM'),6684000,'/STORAGE/MAIN_CATALOGUE/137.pdf','1869060574',2,1,'!-no info-!','Rectangle: 536.0x697.0 (rot: 0 degrees)',1,279,'.pdf',1);
Insert into DOCSTORE.ARTICLES (ID,AUTHOR,SUBJECT,STATUS,TITLE,CREATED,FILE_NAME,MODIFIED,FILE_SIZE,PATH,MD5,ID_OWNER,ID_LANG,KEYWORDS,PAGE_SIZE,ID_SHARED_TYPE,NUMBER_OF_PAGES,FILE_TYPE,ID_ARTICLE_TYPE) values (124,'Bruno Lowagie','This example shows how to add metadata','NON_INDEXED','Hello World example',to_date('11-FEB-14','DD-MON-RR'),'pdf_metadata.pdf',to_timestamp('11-FEB-14 12.47.20.088000000 PM','DD-MON-RR HH.MI.SSXFF AM'),1074,'/STORAGE/MAIN_CATALOGUE/124.pdf','1468644005',2,1,'Metadata, iText, PDF','Rectangle: 595.0x842.0 (rot: 0 degrees)',1,1,'.pdf',1);
Insert into DOCSTORE.ARTICLES (ID,AUTHOR,SUBJECT,STATUS,TITLE,CREATED,FILE_NAME,MODIFIED,FILE_SIZE,PATH,MD5,ID_OWNER,ID_LANG,KEYWORDS,PAGE_SIZE,ID_SHARED_TYPE,NUMBER_OF_PAGES,FILE_TYPE,ID_ARTICLE_TYPE) values (125,'madden','! - no data - !','NON_INDEXED','Microsoft Word - vldb-07-5.doc',to_date('11-FEB-14','DD-MON-RR'),'The End of an Architectural Era (It�??s Time for a Complete Rewrite).pdf',to_timestamp('11-FEB-14 01.13.59.219000000 PM','DD-MON-RR HH.MI.SSXFF AM'),2168995,'/STORAGE/MAIN_CATALOGUE/125.pdf','1216322225',2,1,'! - no data - !','Rectangle: 612.0x792.0 (rot: 0 degrees)',1,11,'.pdf',1);
REM INSERTING into DOCSTORE.ARTICLE_TYPE
SET DEFINE OFF;
Insert into DOCSTORE.ARTICLE_TYPE (TYPE_ID,TYPE) values (3,'Review');
Insert into DOCSTORE.ARTICLE_TYPE (TYPE_ID,TYPE) values (2,'Research');
Insert into DOCSTORE.ARTICLE_TYPE (TYPE_ID,TYPE) values (1,'Academic');
REM INSERTING into DOCSTORE."BIN$T21JC3AOQvKcCzYRoyfZGg==$0"
SET DEFINE OFF;
REM INSERTING into DOCSTORE.LANGUAGES
SET DEFINE OFF;
Insert into DOCSTORE.LANGUAGES (ID_LANG,LANGUAGE) values (1,'English');
Insert into DOCSTORE.LANGUAGES (ID_LANG,LANGUAGE) values (2,'Russian');
Insert into DOCSTORE.LANGUAGES (ID_LANG,LANGUAGE) values (3,'Ukrainian');
REM INSERTING into DOCSTORE.SHARED
SET DEFINE OFF;
REM INSERTING into DOCSTORE.SHARED_TYPE
SET DEFINE OFF;
Insert into DOCSTORE.SHARED_TYPE (ID_SHARED_TYPE,SHARED_TYPE) values (1,'NONE');
Insert into DOCSTORE.SHARED_TYPE (ID_SHARED_TYPE,SHARED_TYPE) values (2,'PUBLIC');
REM INSERTING into DOCSTORE.USERS
SET DEFINE OFF;
Insert into DOCSTORE.USERS (USER_ID,USERNAME,PASSWORD,ENABLED) values (3,'bob','bob',1);
Insert into DOCSTORE.USERS (USER_ID,USERNAME,PASSWORD,ENABLED) values (1,'admin','admin',1);
Insert into DOCSTORE.USERS (USER_ID,USERNAME,PASSWORD,ENABLED) values (2,'john','john',1);
REM INSERTING into DOCSTORE.USER_ROLES
SET DEFINE OFF;
Insert into DOCSTORE.USER_ROLES (USER_ROLE_ID,USER_ID,AUTHORITY) values (4,1,'ROLE_USER');
Insert into DOCSTORE.USER_ROLES (USER_ROLE_ID,USER_ID,AUTHORITY) values (1,1,'ROLE_ADMIN');
Insert into DOCSTORE.USER_ROLES (USER_ROLE_ID,USER_ID,AUTHORITY) values (3,3,'ROLE_USER');
Insert into DOCSTORE.USER_ROLES (USER_ROLE_ID,USER_ID,AUTHORITY) values (2,2,'ROLE_USER');
--------------------------------------------------------
--  DDL for Index ARTICLES_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "DOCSTORE"."ARTICLES_PK" ON "DOCSTORE"."ARTICLES" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index ARTICLE_TYPE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "DOCSTORE"."ARTICLE_TYPE_PK" ON "DOCSTORE"."ARTICLE_TYPE" ("TYPE_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;
-- Unable to render INDEX DDL for object DOCSTORE.BIN$93H0X8rcTH+4lL2o9BC40g==$0 with DBMS_METADATA attempting internal generator.
CREATE INDEX "BIN$93H0X8rcTH+4lL2o9BC40g==$0" ON ()
--------------------------------------------------------
--  DDL for Index LANGUAGES_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "DOCSTORE"."LANGUAGES_PK" ON "DOCSTORE"."LANGUAGES" ("ID_LANG") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index SHARED_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "DOCSTORE"."SHARED_PK" ON "DOCSTORE"."SHARED" ("ID_SHARED") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index SHARED_TYPE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "DOCSTORE"."SHARED_TYPE_PK" ON "DOCSTORE"."SHARED_TYPE" ("ID_SHARED_TYPE") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index USERS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "DOCSTORE"."USERS_PK" ON "DOCSTORE"."USERS" ("USER_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index USER_ROLES_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "DOCSTORE"."USER_ROLES_PK" ON "DOCSTORE"."USER_ROLES" ("USER_ROLE_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  Constraints for Table ARTICLES
--------------------------------------------------------

  ALTER TABLE "DOCSTORE"."ARTICLES" ADD CONSTRAINT "ARTICLES_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS"  ENABLE;
 
  ALTER TABLE "DOCSTORE"."ARTICLES" MODIFY ("ID" NOT NULL ENABLE);
 
  ALTER TABLE "DOCSTORE"."ARTICLES" MODIFY ("CREATED" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table ARTICLE_TYPE
--------------------------------------------------------

  ALTER TABLE "DOCSTORE"."ARTICLE_TYPE" ADD CONSTRAINT "ARTICLE_TYPE_PK" PRIMARY KEY ("TYPE_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS"  ENABLE;
 
  ALTER TABLE "DOCSTORE"."ARTICLE_TYPE" MODIFY ("TYPE_ID" NOT NULL ENABLE);
 
  ALTER TABLE "DOCSTORE"."ARTICLE_TYPE" MODIFY ("TYPE" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table LANGUAGES
--------------------------------------------------------

  ALTER TABLE "DOCSTORE"."LANGUAGES" ADD CONSTRAINT "LANGUAGES_PK" PRIMARY KEY ("ID_LANG")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS"  ENABLE;
 
  ALTER TABLE "DOCSTORE"."LANGUAGES" MODIFY ("ID_LANG" NOT NULL ENABLE);
 
  ALTER TABLE "DOCSTORE"."LANGUAGES" MODIFY ("LANGUAGE" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table SHARED
--------------------------------------------------------

  ALTER TABLE "DOCSTORE"."SHARED" ADD CONSTRAINT "SHARED_PK" PRIMARY KEY ("ID_SHARED")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS"  ENABLE;
 
  ALTER TABLE "DOCSTORE"."SHARED" MODIFY ("ID_SHARED" NOT NULL ENABLE);
 
  ALTER TABLE "DOCSTORE"."SHARED" MODIFY ("ID" NOT NULL ENABLE);
 
  ALTER TABLE "DOCSTORE"."SHARED" MODIFY ("ID_OWNER" NOT NULL ENABLE);
 
  ALTER TABLE "DOCSTORE"."SHARED" MODIFY ("ID_GRANTEE" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table SHARED_TYPE
--------------------------------------------------------

  ALTER TABLE "DOCSTORE"."SHARED_TYPE" ADD CONSTRAINT "SHARED_TYPE_PK" PRIMARY KEY ("ID_SHARED_TYPE")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS"  ENABLE;
 
  ALTER TABLE "DOCSTORE"."SHARED_TYPE" MODIFY ("ID_SHARED_TYPE" NOT NULL ENABLE);
 
  ALTER TABLE "DOCSTORE"."SHARED_TYPE" MODIFY ("SHARED_TYPE" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table USERS
--------------------------------------------------------

  ALTER TABLE "DOCSTORE"."USERS" MODIFY ("USER_ID" NOT NULL ENABLE);
 
  ALTER TABLE "DOCSTORE"."USERS" MODIFY ("USERNAME" NOT NULL ENABLE);
 
  ALTER TABLE "DOCSTORE"."USERS" MODIFY ("PASSWORD" NOT NULL ENABLE);
 
  ALTER TABLE "DOCSTORE"."USERS" MODIFY ("ENABLED" NOT NULL ENABLE);
 
  ALTER TABLE "DOCSTORE"."USERS" ADD CONSTRAINT "USERS_PK" PRIMARY KEY ("USER_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table USER_ROLES
--------------------------------------------------------

  ALTER TABLE "DOCSTORE"."USER_ROLES" MODIFY ("USER_ROLE_ID" NOT NULL ENABLE);
 
  ALTER TABLE "DOCSTORE"."USER_ROLES" MODIFY ("USER_ID" NOT NULL ENABLE);
 
  ALTER TABLE "DOCSTORE"."USER_ROLES" MODIFY ("AUTHORITY" NOT NULL ENABLE);
 
  ALTER TABLE "DOCSTORE"."USER_ROLES" ADD CONSTRAINT "USER_ROLES_PK" PRIMARY KEY ("USER_ROLE_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table ARTICLES
--------------------------------------------------------

  ALTER TABLE "DOCSTORE"."ARTICLES" ADD CONSTRAINT "ARTICLES_FK" FOREIGN KEY ("ID_OWNER")
	  REFERENCES "DOCSTORE"."USERS" ("USER_ID") ENABLE;
 
  ALTER TABLE "DOCSTORE"."ARTICLES" ADD CONSTRAINT "ARTICLES_FK2" FOREIGN KEY ("ID_LANG")
	  REFERENCES "DOCSTORE"."LANGUAGES" ("ID_LANG") ENABLE;
 
  ALTER TABLE "DOCSTORE"."ARTICLES" ADD CONSTRAINT "ARTICLES_FK3" FOREIGN KEY ("ID_SHARED_TYPE")
	  REFERENCES "DOCSTORE"."SHARED_TYPE" ("ID_SHARED_TYPE") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table SHARED
--------------------------------------------------------

  ALTER TABLE "DOCSTORE"."SHARED" ADD CONSTRAINT "SHARED_FK" FOREIGN KEY ("ID")
	  REFERENCES "DOCSTORE"."ARTICLES" ("ID") ENABLE;
 
  ALTER TABLE "DOCSTORE"."SHARED" ADD CONSTRAINT "SHARED_FK2" FOREIGN KEY ("ID_OWNER")
	  REFERENCES "DOCSTORE"."USERS" ("USER_ID") ENABLE;
 
  ALTER TABLE "DOCSTORE"."SHARED" ADD CONSTRAINT "SHARED_FK3" FOREIGN KEY ("ID_GRANTEE")
	  REFERENCES "DOCSTORE"."USERS" ("USER_ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table USER_ROLES
--------------------------------------------------------

  ALTER TABLE "DOCSTORE"."USER_ROLES" ADD CONSTRAINT "USER_ROLES_FK" FOREIGN KEY ("USER_ID")
	  REFERENCES "DOCSTORE"."USERS" ("USER_ID") ENABLE;
--------------------------------------------------------
--  DDL for Trigger BI_ARTICLES
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "DOCSTORE"."BI_ARTICLES" 
  before insert on "ARTICLES"               
  for each row  
begin   
    select "ARTICLES_SEQ".nextval into :NEW.ID from dual; 
end; 

/
ALTER TRIGGER "DOCSTORE"."BI_ARTICLES" DISABLE;
--------------------------------------------------------
--  DDL for Trigger BI_ARTICLE_TYPE
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "DOCSTORE"."BI_ARTICLE_TYPE" 
  before insert on "ARTICLE_TYPE"               
  for each row  
begin   
    select "ARTICLE_TYPE_SEQ".nextval into :NEW.TYPE_ID from dual; 
end; 

/
ALTER TRIGGER "DOCSTORE"."BI_ARTICLE_TYPE" ENABLE;
--------------------------------------------------------
--  DDL for Trigger BI_LANGUAGES
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "DOCSTORE"."BI_LANGUAGES" 
  before insert on "LANGUAGES"               
  for each row  
begin   
    select "LANGUAGES_SEQ".nextval into :NEW.ID_LANG from dual; 
end; 

/
ALTER TRIGGER "DOCSTORE"."BI_LANGUAGES" ENABLE;
--------------------------------------------------------
--  DDL for Trigger BI_SHARED
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "DOCSTORE"."BI_SHARED" 
  before insert on "SHARED"               
  for each row  
begin   
    select "SHARED_SEQ".nextval into :NEW.ID_SHARED from dual; 
end; 

/
ALTER TRIGGER "DOCSTORE"."BI_SHARED" DISABLE;
--------------------------------------------------------
--  DDL for Trigger BI_SHARED_TYPE
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "DOCSTORE"."BI_SHARED_TYPE" 
  before insert on "SHARED_TYPE"               
  for each row  
begin   
    select "SHARED_TYPE_SEQ".nextval into :NEW.ID_SHARED_TYPE from dual; 
end; 

/
ALTER TRIGGER "DOCSTORE"."BI_SHARED_TYPE" ENABLE;
--------------------------------------------------------
--  DDL for Trigger BI_USERS
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "DOCSTORE"."BI_USERS" 
  before insert on "USERS"               
  for each row  
begin   
    select "USERS_SEQ".nextval into :NEW.USER_ID from dual; 
end; 

/
ALTER TRIGGER "DOCSTORE"."BI_USERS" ENABLE;
--------------------------------------------------------
--  DDL for Trigger BI_USER_ROLES
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "DOCSTORE"."BI_USER_ROLES" 
  before insert on "USER_ROLES"               
  for each row  
begin   
    select "USER_ROLES_SEQ".nextval into :NEW.USER_ROLE_ID from dual; 
end; 

/
ALTER TRIGGER "DOCSTORE"."BI_USER_ROLES" ENABLE;
