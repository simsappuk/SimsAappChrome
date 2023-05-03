-- Table: email_config and provision

-- Adding a new Column

ALTER TABLE email_config ADD COLUMN secure_smtp_connection boolean default false;

ALTER TABLE email_config Drop COLUMN mail_prop_auth;
ALTER TABLE email_config Drop COLUMN mail_prop_socker_factory_class;
ALTER TABLE email_config Drop COLUMN mail_prop_socker_factory_port;
ALTER TABLE email_config Drop COLUMN mail_prop_socket_factory_fallback;