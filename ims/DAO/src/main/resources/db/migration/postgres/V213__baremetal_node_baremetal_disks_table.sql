-- Author: Intesar Mohammed


-- Table: baremetal_nodes_baremetal_disks

-- DROP TABLE baremetal_nodes_baremetal_disks;


CREATE TABLE baremetal_nodes_baremetal_disks
(
  baremetal_nodes_id character varying(255) NOT NULL,
  disk_id character varying(255),
  capacity_mb double precision,
  media_type character varying(255),
  model character varying(255),
  name character varying(255),
  current_temperature_celsius integer,
  encrypted bool,
  description character varying(512),
  interface_type character varying(255),
  interface_speed_mbps integer,
  location character varying(255),
  status_health character varying(255),
  status_state character varying(255),
  used character varying(255),
  CONSTRAINT fk_dofdf9eh2g1nxsdmw9in3i6bm FOREIGN KEY (baremetal_nodes_id)
      REFERENCES public.baremetal_nodes (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


