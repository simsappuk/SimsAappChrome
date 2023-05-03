-- author Intesar Mohammed
-- Create Tables login_audit


CREATE TABLE login_audit (
    id character varying(255) NOT NULL,
    created_date timestamp without time zone,

    user_name character varying(255),
    ip_address character varying(255),
    user_agent character varying(510),
    login_success boolean
);