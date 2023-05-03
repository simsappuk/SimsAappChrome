
-- Table: public."user"

-- DROP TABLE public."user";

CREATE TABLE public.users(
  user_id character varying(255) NOT NULL,
  email character varying(255),
  password character varying(255) NOT NULL,
  name character varying(255),
  last_name character varying(255),
  inactive boolean,
  contact_information character varying(255),
  mobile_number character varying(255),
  CONSTRAINT users_pkey PRIMARY KEY(user_id))
WITH (
  OIDS=FALSE
);




-- Table: public.role

-- DROP TABLE public.role;

CREATE TABLE public.role
(
  id character varying(255)NOT NULL,
  role character varying(255),
  CONSTRAINT role_pkey PRIMARY KEY(id)
);





-- Table: public.user_role

-- DROP TABLE public.user_role;

CREATE TABLE public.users_role
(
  user_id character varying(255) NOT NULL,
  id character varying(255) NOT NULL,
  CONSTRAINT user_role PRIMARY KEY (user_id, id),
  CONSTRAINT qwqwqwqwqwy FOREIGN KEY (user_id)
      REFERENCES public.users (user_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT wwqqwqwq FOREIGN KEY (id)
      REFERENCES public.role (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);



--CREATE INDEX FKwwqqwqwq ON users_role;

INSERT INTO public.users(user_id,email,password,name,last_name,inactive,contact_information,mobile_number) VALUES('1','admin@gmail.com','$2a$10$gw88y1X9wLsJTbaFni0Aj.sODAO1jy2fPr2QiwLtCZjaLsQPKvd/y','admin','admin',false,'contactinfo','707557757');
INSERT INTO public.role(id,role) VALUES('1','ADMIN');
INSERT INTO public.role(id,role) VALUES('2','USER');
INSERT INTO public.role(id,role) VALUES('3','DBA');
INSERT INTO public.users_role(user_id,id) VALUES('1','1');