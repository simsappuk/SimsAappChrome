-- Container_Container_Networks
-- Author: Intesar Mohammed


CREATE TABLE container_container_networks (

    container_id character varying(255) NOT NULL,
    name character varying(255),
    aliases character varying(255),
    network_id character varying(255),
    endpoint_id character varying(255),
    gateway character varying(255),
    ip_address character varying(255),
    ip_prefix_len integer,
    ip_v6_gateway character varying(255),
    global_ipv6_address character varying(255),
    global_ipv6_prefix_len character varying(255),
    mac_address character varying(255)

);

ALTER TABLE ONLY container_container_networks
    ADD CONSTRAINT fk_b6gpajyaijjvdk4k9lt9wt8911 FOREIGN KEY (container_id) REFERENCES container(id);
