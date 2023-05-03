-- Table: build_plugin_profiles

-- DROP TABLE build_plugin_profiles;

CREATE TABLE build_plugin_profiles
(
  build_id character varying(255) NOT NULL,
  plugin_profiles_id character varying(255) NOT NULL,
  CONSTRAINT fk_74cyfeam81rjjxd7ihweadx3u FOREIGN KEY (plugin_profiles_id)
      REFERENCES plugin_profile (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_dn47hn84jdhd6q57awhdp86cy FOREIGN KEY (build_id)
      REFERENCES build (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT uk_74cyfeam81rjjxd7ihweadx3u UNIQUE (plugin_profiles_id)
);


-- Rename column next_cron_date to next_cron_date_
ALTER TABLE build_plugin_profile RENAME COLUMN next_cron_date TO next_cron_date_;

ALTER TABLE build_plugin_profile drop COLUMN active;
  
  
