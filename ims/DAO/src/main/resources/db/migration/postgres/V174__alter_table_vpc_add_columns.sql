-- Author: Mohammed Luqman Shareef
alter table virtual_private_cloud add column firewall_id character varying(255);
alter table virtual_private_cloud add column firewall_ip character varying(255);
alter table virtual_private_cloud add column firewall_username character varying(255);
alter table virtual_private_cloud add column firewall_password character varying(255);

alter table virtual_private_cloud add column description character varying(255);

insert into app_config (id, key_, value_, description) values ('firewall.blueprint.id', 'firewall.blueprint.id', '', 'Firewall VM Blueprint Id');
