-- Table: container
-- Add 16 columns

ALTER TABLE container ADD COLUMN cgroup_parent character varying(255);
ALTER TABLE container ADD COLUMN cpu_quota character varying(255);
ALTER TABLE container ADD COLUMN cpu_set character varying(255);
ALTER TABLE container ADD COLUMN stop_signal character varying(255);
ALTER TABLE container ADD COLUMN ipc character varying(255);
ALTER TABLE container ADD COLUMN mac_address character varying(255);
ALTER TABLE container ADD COLUMN memswap_limit integer;
ALTER TABLE container ADD COLUMN read_only boolean;


CREATE TABLE container_devices (
    container_id character varying(255) NOT NULL,
    devices character varying(255)
);

ALTER TABLE ONLY container_devices
    ADD CONSTRAINT fk_hfuy9b1ltugu1eg6ne7kfvedv FOREIGN KEY (container_id) REFERENCES container(id);


CREATE TABLE container_entry_points (
    container_id character varying(255) NOT NULL,
    entry_points character varying(255)
);

ALTER TABLE ONLY container_entry_points
    ADD CONSTRAINT fk_hfuy9b1ltugu1eg6ne7kfveep FOREIGN KEY (container_id) REFERENCES container(id);


CREATE TABLE container_external_links (
    container_id character varying(255) NOT NULL,
    external_links character varying(255)
);

ALTER TABLE ONLY container_external_links
    ADD CONSTRAINT fk_hfuy9b1ltugu1eg6ne7kfveel FOREIGN KEY (container_id) REFERENCES container(id);


CREATE TABLE container_extra_hosts (
    container_id character varying(255) NOT NULL,
    extra_hosts character varying(255)
);

ALTER TABLE ONLY container_extra_hosts
    ADD CONSTRAINT fk_hfuy9b1ltugu1eg6ne7kfveeh FOREIGN KEY (container_id) REFERENCES container(id);


CREATE TABLE container_labels (
    container_id character varying(255) NOT NULL,
    labels character varying(255)
);

ALTER TABLE ONLY container_labels
    ADD CONSTRAINT fk_hfuy9b1ltugu1eg6ne7kfvelb FOREIGN KEY (container_id) REFERENCES container(id);


CREATE TABLE container_networks (
    container_id character varying(255) NOT NULL,
    networks character varying(255)
);

ALTER TABLE ONLY container_networks
    ADD CONSTRAINT fk_hfuy9b1ltugu1eg6ne7kfvent FOREIGN KEY (container_id) REFERENCES container(id);


CREATE TABLE container_security_opt (
    container_id character varying(255) NOT NULL,
    security_opt character varying(255)
);

ALTER TABLE ONLY container_security_opt
    ADD CONSTRAINT fk_hfuy9b1ltugu1eg6ne7kfveso FOREIGN KEY (container_id) REFERENCES container(id);


CREATE TABLE container_ulimits (
    container_id character varying(255) NOT NULL,
    ulimits character varying(255)
);

ALTER TABLE ONLY container_ulimits
    ADD CONSTRAINT fk_hfuy9b1ltugu1eg6ne7kfveul FOREIGN KEY (container_id) REFERENCES container(id);



