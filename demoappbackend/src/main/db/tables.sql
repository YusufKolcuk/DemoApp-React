\echo ========================= Creating Tables ==============================
SET SEARCH_PATH TO demoappreact;

CREATE TABLE persons (
    id	            	BIGSERIAL,
    username      		character varying(255),
	displayname			character varying(255),
	email				character varying(255),
	password			character varying(255)
);
ALTER TABLE persons OWNER TO :usr;


CREATE TABLE cities (
    id				bigint NOT NULL UNIQUE,
    name			character varying(255),
    temp			double precision,
    tempmin			double precision,
    tempmax			double precision,
    pressure		double precision,
    humidity		double precision,
    descr			character varying(255),
    lat				double precision,
    lon				double precision
);
ALTER TABLE cities OWNER TO :usr;


CREATE TABLE roles (
    id	            bigint NOT NULL,
	name			character varying(255)
);
ALTER TABLE roles OWNER TO :usr;


CREATE TABLE user_roles (
    usr_id	        bigint,
	role_id			bigint
);
ALTER TABLE user_roles OWNER TO :usr;

