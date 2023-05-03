
-- DigitalOcean Machine Compose Blueprints

INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk, uuid,
tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name, version, yml, owner_id,
tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description, external_link)
VALUES (' 4028818650d4aca10150d4bf63470001', 'admin',
'2015-12-08 15:05:24.039', 'admin', '2015-12-08 15:09:12.139', false, false, 1, '402881834d9ee4d1014d9ee5d73f0014',
'4028818650d4aca10150d4bf63470001', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER', 'OWNER', 'VM_COMPOSE', NULL,
'DigitalOcean Ubuntu 14.04 Small SFO', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '1.0',
'# Note: Some of the fields are optional based on provider and can be left empty.
# Small - This is a user defined name.
Small:
  # Digital Ocean region name San Francisco
  region: sfo1
  # Optional
  description:
  # Cloud provider machine type
  instanceType: 1gb
  # Cloud provider image
  image: sfo1/ubuntu-14-04-x64
  # Total VMs to spin - Default is 1
  count: 1
  ', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, false,
  'Ubuntu 14.04 x64 1gb in San Francisco', NULL);



INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk, uuid,
tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name, version, yml, owner_id,
tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description, external_link)
VALUES ('4028818650d4aca10150d4bf63470002', 'admin', '2015-12-08 15:05:24.039', 'admin', '2015-12-08 15:09:12.139', false, false, 1,
'402881834d9ee4d1014d9ee5d73f0014', '4028818650d4aca10150d4bf63470002', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER', 'OWNER',
'VM_COMPOSE', NULL, 'DigitalOcean Ubuntu 14.04 Medium NYC', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '1.0',
'# Note: Some of the fields are optional based on provider and can be left empty.
# Medium - This is a user defined name.
Medium:
  # Digital Ocean region name New York City
  region: nyc1
  # Optional
  description:
  # Cloud provider machine type
  instanceType: 2gb
  # Cloud provider image
  image: nyc1/ubuntu-14-04-x64
 # Total VMs to spin - Default is 1
  count: 1
  ', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, false,
  'Ubuntu 14.04 x64 2gb in New York City', NULL);



INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk, uuid,
tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name, version, yml, owner_id,
tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description, external_link)
VALUES ('4028818650d4aca10150d4bf63470003', 'admin', '2015-12-08 15:05:24.039', 'admin', '2015-12-08 15:09:12.139', false, false, 1,
'402881834d9ee4d1014d9ee5d73f0014', '4028818650d4aca10150d4bf63470003', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER', 'OWNER', 'VM_COMPOSE',
NULL, 'DigitalOcean Ubuntu 14.04 Large FRA', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '1.0',
'# Note: Some of the fields are optional based on provider and can be left empty.
# Large - This is a user defined name.
Large:
  # Digital Ocean region name Frankfurt
  region: fra1
  # Optional
  description:
  # Cloud provider machine type
  instanceType: 4gb
  # Cloud provider image
  image: fra1/ubuntu-14-04-x64
  # Total VMs to spin - Default is 1
  count: 1
  ', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, false,
  'Ubuntu 14.04 x64 4gb in Frankfurt', NULL);



INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk, uuid,
tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name, version, yml, owner_id,
 tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description, external_link)
 VALUES ('4028818650d4aca10150d4bf63470004', 'admin', '2015-12-08 15:05:24.039', 'admin', '2015-12-08 15:09:12.139', false, false, 1,
 '402881834d9ee4d1014d9ee5d73f0014', '4028818650d4aca10150d4bf63470004', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER', 'OWNER',
 'VM_COMPOSE', NULL, 'DigitalOcean Ubuntu 14.04 Large NYC2', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '1.0',
 '# Note: Some of the fields are optional based on provider and can be left empty.
# Large - This is a user defined name.
Large:
  # Digital Ocean region name New York City2
  region: nyc2
  # Optional
  description:
  # Cloud provider machine type
  instanceType: 4gb
  # Cloud provider image
  image: nyc2/ubuntu-14-04-x64
 # Total VMs to spin - Default is 1
  count: 1
  ', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, false,
  'Ubuntu 14.04 x64 4gb in New York City2', NULL);



INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk, uuid,
tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name, version, yml, owner_id,
 tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description, external_link)
 VALUES ('4028818650d4aca10150d4bf63470005', 'admin', '2015-12-08 15:05:24.039', 'admin', '2015-12-08 15:09:12.139', false, false, 1,
 '402881834d9ee4d1014d9ee5d73f0014', '4028818650d4aca10150d4bf63470005', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER', 'OWNER',
 'VM_COMPOSE', NULL, 'DigitalOcean Ubuntu 14.04 XLarge LON', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '1.0',
 '# Note: Some of the fields are optional based on provider and can be left empty.
# XLarge - This is a user defined name.
XLarge:
  # Digital Ocean region name London
  region: lon1
  # Optional
  description:
  # Cloud provider machine type
  instanceType: 8gb
  # Cloud provider image
  image: lon1/ubuntu-14-04-x64
 # Total VMs to spin - Default is 1
  count: 1
  ', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, false,
  'Ubuntu 14.04 x64 8gb in London', NULL);




INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk,
uuid, tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name, version, yml,
owner_id, tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description, external_link)
VALUES ('4028818650d4aca10150d4bf63470006', 'admin', '2015-12-08 15:05:24.039', 'admin', '2015-12-08 15:09:12.139', false, false, 1,
'402881834d9ee4d1014d9ee5d73f0014', '4028818650d4aca10150d4bf63470006', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER', 'OWNER',
'VM_COMPOSE', NULL, 'DigitalOcean Ubuntu 14.04 2XLarge SGP', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '1.0',
'# Note: Some of the fields are optional based on provider and can be left empty.
# 2XLarge - This is a user defined name.
2XLarge:
  # Digital Ocean region name Singapore
  region: sgp1
  # Optional
  description:
  # Cloud provider machine type
  instanceType: 16gb
  # Cloud provider image
  image: sgp1/ubuntu-14-04-x64
  # Total VMs to spin - Default is 1
  count: 1
  ', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, false,
  'Ubuntu 14.04 x64 16gb in Singapore', NULL);



INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk,
uuid, tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name, version, yml,
owner_id, tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description, external_link)
VALUES ('4028818650d4aca10150d4bf63470007', 'admin', '2015-12-08 15:05:24.039', 'admin', '2015-12-08 15:09:12.139', false, false, 1,
'402881834d9ee4d1014d9ee5d73f0014', '4028818650d4aca10150d4bf63470007', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER', 'OWNER',
'VM_COMPOSE', NULL, 'DigitalOcean Ubuntu 14.04 3XLarge SFO', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '1.0',
'# Note: Some of the fields are optional based on provider and can be left empty.
# 3XLarge - This is a user defined name.
3XLarge:
  # Digital Ocean region name San Francisco
  region: sfo1
  # Optional
  description:
  # Cloud provider machine type
  instanceType: 32gb
  # Cloud provider image
  image: sfo1/ubuntu-14-04-x64
  # Total VMs to spin - Default is 1
  count: 1
  ', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, false,
  'Ubuntu 14.04 x64 32gb in San Francisco', NULL);


INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk,
uuid, tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name, version, yml,
owner_id, tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description, external_link)
VALUES ('4028818650d4aca10150d4bf63470008', 'admin', '2015-12-08 15:05:24.039', 'admin', '2015-12-08 15:09:12.139', false, false, 1,
'402881834d9ee4d1014d9ee5d73f0014', '4028818650d4aca10150d4bf63470008', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER', 'OWNER',
'VM_COMPOSE', NULL, 'DigitalOcean Ubuntu 14.04 4XLarge AMS', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '1.0',
'# Note: Some of the fields are optional based on provider and can be left empty.
# 4XLarge - This is a user defined name.
4XLarge:
  # Digital Ocean region name Amsterdam
  region: ams2
  # Optional
  description:
  # Cloud provider machine type
  instanceType: 64gb
  # Cloud provider image
  image: ams2/ubuntu-14-04-x64
  # Total VMs to spin - Default is 1
  count: 1
  ', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, false,
  'Ubuntu 14.04 x64 64gb in Amsterdam', NULL);



-- Rackspace Machine Compose Blueprints

INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk,
uuid, tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name, version, yml,
owner_id, tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description, external_link)
VALUES ('4028818650d4aca10150d4bf63470009', 'admin', '2015-12-08 15:05:24.039', 'admin', '2015-12-08 15:09:12.139', false, false, 1,
'402881834d9ee4d1014d9ee5d73f0014', '4028818650d4aca10150d4bf63470009', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER', 'OWNER', 'VM_COMPOSE',
NULL, 'Rackspace Ubuntu 14.04 Small IAD', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '1.0',
'# Note: Some of the fields are optional based on provider and can be left empty.
# Small - This is a user defined name.
Small:
  # Rackspace region name Northern Virginia
  region: IAD
  # Optional
  description:
  # Cloud provider machine type
  instanceType: general1-1
  # Cloud provider image
  image: IAD/5ed162cc-b4eb-4371-b24a-a0ae73376c73
  # Total VMs to spin - Default is 1
  count: 1
  ', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, false,
  'Ubuntu 14.04 x64 1gb in Northern Virginia', NULL);



INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk, uuid,
tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name, version, yml, owner_id,
tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description, external_link)
VALUES ('4028818650d4aca10150d4bf63470010', 'admin', '2015-12-08 15:05:24.039', 'admin', '2015-12-08 15:09:12.139', false, false, 1,
'402881834d9ee4d1014d9ee5d73f0014', '4028818650d4aca10150d4bf63470010', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER', 'OWNER',
'VM_COMPOSE', NULL, 'Rackspace Ubuntu 14.04 Medium DFW', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '1.0',
'# Note: Some of the fields are optional based on provider and can be left empty.
# Medium - This is a user defined name.
Medium:
  # Rackspace region name Dallas
  region: DFW
  # Optional
  description:
  # Cloud provider machine type
  instanceType: general1-2
  # Cloud provider image
  image: DFW/09de0a66-3156-48b4-90a5-1cf25a905207
  # Total VMs to spin - Default is 1
  count: 1
  ', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, false,
  'Ubuntu 14.04 x64 2gb in Dallas', NULL);




INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk, uuid,
tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name, version, yml, owner_id,
tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description, external_link)
VALUES ('4028818650d4aca10150d4bf63470011', 'admin', '2015-12-08 15:05:24.039', 'admin', '2015-12-08 15:09:12.139', false, false, 1,
'402881834d9ee4d1014d9ee5d73f0014', '4028818650d4aca10150d4bf63470011', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER', 'OWNER',
'VM_COMPOSE', NULL, 'Rackspace Ubuntu 14.04 Large DFW', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '1.0',
'# Note: Some of the fields are optional based on provider and can be left empty.
# Large - This is a user defined name.
Large:
  # Rackspace region name Dallas
  region: DFW
  # Optional
  description:
  # Cloud provider machine type
  instanceType: general1-4
  # Cloud provider image
  image: DFW/09de0a66-3156-48b4-90a5-1cf25a905207
  # Total VMs to spin - Default is 1
  count: 1
  ', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, false,
  'Ubuntu 14.04 x64 4gb in Dallas', NULL);




INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk, uuid,
tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name, version, yml, owner_id,
tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description, external_link)
VALUES ('4028818650d4aca10150d4bf63470012', 'admin', '2015-12-08 15:05:24.039', 'admin', '2015-12-08 15:09:12.139', false, false, 1,
'402881834d9ee4d1014d9ee5d73f0014', '4028818650d4aca10150d4bf63470012', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER', 'OWNER',
'VM_COMPOSE', NULL, 'Rackspace Ubuntu 14.04 2XLarge SYD', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '1.0',
'# Note: Some of the fields are optional based on provider and can be left empty.
# 2XLarge - This is a user defined name.
2XLarge:
  # Rackspace region name Sydney
  region: SYD
  # Optional
  description:
  # Cloud provider machine type
  instanceType: 6
  # Cloud provider image
  image: SYD/5ed162cc-b4eb-4371-b24a-a0ae73376c73
  # Total VMs to spin - Default is 1
  count: 1
  ', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, false,
  'Ubuntu 14.04 x64 8gb in Sydney', NULL);




INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk,
uuid, tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name, version, yml,
owner_id, tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description, external_link)
VALUES ('4028818650d4aca10150d4bf63470013', 'admin', '2015-12-08 15:05:24.039', 'admin', '2015-12-08 15:09:12.139', false, false, 1,
'402881834d9ee4d1014d9ee5d73f0014', '4028818650d4aca10150d4bf63470013', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER', 'OWNER', 'VM_COMPOSE',
NULL, 'Rackspace Ubuntu 14.04 3XLarge HKG', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '1.0',
'# Note: Some of the fields are optional based on provider and can be left empty.
# 3XLarge - This is a user defined name.
3XLarge:
  # Rackspace region name Hong Kong
  region: HKG
  # Optional
  description:
  # Cloud provider machine type
  instanceType: 7
  # Cloud provider image
  image: HKG/09de0a66-3156-48b4-90a5-1cf25a905207
  # Total VMs to spin - Default is 1
  count: 1
  ', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, false,
  'Ubuntu 14.04 x64 15gb in Hong Kong', NULL);




INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk, uuid,
tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name, version, yml, owner_id,
tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description, external_link)
VALUES ('4028818650d4aca10150d4bf63470014', 'admin', '2015-12-08 15:05:24.039', 'admin', '2015-12-08 15:09:12.139', false, false, 1,
'402881834d9ee4d1014d9ee5d73f0014', '4028818650d4aca10150d4bf63470014', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER', 'OWNER',
'VM_COMPOSE', NULL, 'Rackspace Ubuntu 14.04 4XLarge IAD', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '1.0',
'# Note: Some of the fields are optional based on provider and can be left empty.
# 4XLarge - This is a user defined name.
4XLarge:
  # Rackspace region name Northern Virginia
  region: IAD
  # Optional
  description:
  # Cloud provider machine type
  instanceType: 8
  # Cloud provider image
  image: IAD/5ed162cc-b4eb-4371-b24a-a0ae73376c73
  # Total VMs to spin - Default is 1
  count: 1
  ', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, false,
  'Ubuntu 14.04 x64 30gb in Northern Virginia', NULL);




INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk, uuid,
tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name, version, yml, owner_id,
tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description, external_link)
VALUES ('4028818650d4aca10150d4bf63470015', 'admin', '2015-12-08 15:05:24.039', 'admin', '2015-12-08 15:09:12.139', false, false, 1,
'402881834d9ee4d1014d9ee5d73f0014', '4028818650d4aca10150d4bf63470015', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER', 'OWNER', 'VM_COMPOSE',
NULL, 'Rackspace Ubuntu 14.04 5XLarge DFW', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '1.0',
'# Note: Some of the fields are optional based on provider and can be left empty.
# 5XLarge - This is a user defined name.
5XLarge:
  # Rackspace region name Dallas
  region: DFW
  # Optional
  description:
  # Cloud provider machine type
  instanceType: performance2-60
  # Cloud provider image
  image: DFW/09de0a66-3156-48b4-90a5-1cf25a905207
  # Total VMs to spin - Default is 1
  count: 1
  ', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, false,
  'Ubuntu 14.04 x64 60gb in Dallas', NULL);




INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk, uuid,
tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name, version, yml, owner_id,
tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description, external_link)
VALUES ('4028818650d4aca10150d4bf63470016', 'admin', '2015-12-08 15:05:24.039', 'admin', '2015-12-08 15:09:12.139', false, false, 1,
'402881834d9ee4d1014d9ee5d73f0014', '4028818650d4aca10150d4bf63470016', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER', 'OWNER', 'VM_COMPOSE',
 NULL, 'Rackspace Ubuntu 14.04 6XLarge HKG', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '1.0',
 '# Note: Some of the fields are optional based on provider and can be left empty.
# 6XLarge - This is a user defined name.
6XLarge:
  # Rackspace region name Hong Kong
  region: HKG
  # Optional
  description:
  # Cloud provider machine type
  instanceType: performance2-120
  # Cloud provider image
  image: HKG/09de0a66-3156-48b4-90a5-1cf25a905207
  # Total VMs to spin - Default is 1
  count: 1
  ', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, false,
  'Ubuntu 14.04 x64 120gb in Hong Kong', NULL);





-- IBM SoftLayer Machine Compose Blueprints

INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk, uuid,
tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name, version, yml, owner_id,
 tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description, external_link)
 VALUES ('4028818650d4aca10150d4bf63470017', 'admin', '2015-12-08 15:05:24.039', 'admin', '2015-12-08 15:09:12.139', false, false, 1,
 '402881834d9ee4d1014d9ee5d73f0014', '4028818650d4aca10150d4bf63470017', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER', 'OWNER',
 'VM_COMPOSE', NULL, 'IBM SoftLayer Ubuntu 14 Small SJC', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '1.0',
 '# Note: Some of the fields are optional based on provider and can be left empty.
# Small - This is a user defined name.
Small:
  # IBM SoftLayer region name San Jose
  region: sjc01
  # Optional
  description:
  # Cloud provider machine type
  instanceType: cpu=1,memory=1024,disk=25,type=SAN
  # Cloud provider image
  image: sjc01/UBUNTU_14_64
  # Total VMs to spin - Default is 1
  count: 1
  ', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, false,
  'Ubuntu 14 x64 1gb in San Jose', NULL);




INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk,
 uuid, tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name, version, yml,
 owner_id, tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description, external_link)
 VALUES ('4028818650d4aca10150d4bf63470018', 'admin', '2015-12-08 15:05:24.039', 'admin', '2015-12-08 15:09:12.139', false, false, 1,
 '402881834d9ee4d1014d9ee5d73f0014', '4028818650d4aca10150d4bf63470018', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER', 'OWNER',
 'VM_COMPOSE', NULL, 'IBM SoftLayer Ubuntu 14 Medium DAL', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '1.0',
 '# Note: Some of the fields are optional based on provider and can be left empty.
# Medium - This is a user defined name.
Medium:
  # IBM SoftLayer region name Dallas
  region: dal01
  # Optional
  description:
  # Cloud provider machine type
  instanceType: cpu=2,memory=2048,disk=100,type=SAN
  # Cloud provider image
  image: dal01/UBUNTU_14_64
  # Total VMs to spin - Default is 1
  count: 1
  ', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, false,
  'Ubuntu 14 x64 2gb in Dallas', NULL);




INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk,
 uuid, tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name, version, yml,
  owner_id, tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description, external_link)
   VALUES ('4028818650d4aca10150d4bf63470019', 'admin', '2015-12-08 15:05:24.039', 'admin', '2015-12-08 15:09:12.139', false, false, 1,
    '402881834d9ee4d1014d9ee5d73f0014', '4028818650d4aca10150d4bf63470019', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER', 'OWNER',
    'VM_COMPOSE', NULL, 'IBM SoftLayer Ubuntu 14 XLarge SEA', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '1.0',
    '# Note: Some of the fields are optional based on provider and can be left empty.
# XLarge - This is a user defined name.
XLarge:
  # IBM SoftLayer region name Seattle
  region: sea01
  # Optional
  description:
  # Cloud provider machine type
  instanceType: cpu=4,memory=8192,disk=100,type=SAN
  # Cloud provider image
  image: sea01/UBUNTU_14_64
  # Total VMs to spin - Default is 1
  count: 1
  ', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, false,
  'Ubuntu 14 x64 8gb in Seattle', NULL);




INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk, uuid,
 tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name, version, yml,
  owner_id, tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description, external_link)
  VALUES ('4028818650d4aca10150d4bf63470020', 'admin', '2015-12-08 15:05:24.039', 'admin', '2015-12-08 15:09:12.139', false, false, 1,
  '402881834d9ee4d1014d9ee5d73f0014', '4028818650d4aca10150d4bf63470020', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER', 'OWNER',
  'VM_COMPOSE', NULL, 'IBM SoftLayer Ubuntu 14 2XLarge TOR', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '1.0',
  '# Note: Some of the fields are optional based on provider and can be left empty.
# 2XLarge - This is a user defined name.
2XLarge:
  # IBM SoftLayer region name Toronto
  region: tor01
  # Optional
  description:
  # Cloud provider machine type
  instanceType: cpu=8,memory=12288,disk=100,type=SAN
  # Cloud provider image
  image: tor01/UBUNTU_14_64
  # Total VMs to spin - Default is 1
  count: 1
  ', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, false,
   'Ubuntu 14 x64 12gb in Toronto', NULL);




INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk,
 uuid, tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name, version,
  yml, owner_id, tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description, external_link)
   VALUES ('4028818650d4aca10150d4bf63470021', 'admin', '2015-12-08 15:05:24.039', 'admin', '2015-12-08 15:09:12.139', false, false, 1,
   '402881834d9ee4d1014d9ee5d73f0014', '4028818650d4aca10150d4bf63470021', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER', 'OWNER',
   'VM_COMPOSE', NULL, 'IBM SoftLayer Ubuntu 14 3XLarge PAR', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '1.0',
   '# Note: Some of the fields are optional based on provider and can be left empty.
# 3XLarge - This is a user defined name.
3XLarge:
  # IBM SoftLayer region name Paris
  region: par01
  # Optional
  description:
  # Cloud provider machine type
  instanceType: cpu=8,memory=16384,disk=100,type=SAN
  # Cloud provider image
  image: par01/UBUNTU_14_64
  # Total VMs to spin - Default is 1
  count: 1
  ', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, false,
  'Ubuntu 14 x64 16gb in Paris', NULL);





INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk, uuid,
tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name, version, yml, owner_id,
tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description, external_link)
VALUES ('4028818650d4aca10150d4bf63470022', 'admin', '2015-12-08 15:05:24.039', 'admin', '2015-12-08 15:09:12.139', false, false, 1,
'402881834d9ee4d1014d9ee5d73f0014', '4028818650d4aca10150d4bf63470022', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER', 'OWNER',
'VM_COMPOSE', NULL, 'IBM SoftLayer Ubuntu 14 4XLarge CHE', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '1.0',
'# Note: Some of the fields are optional based on provider and can be left empty.
# 4XLarge - This is a user defined name.
4XLarge:
  # IBM SoftLayer region name Chennai
  region: che01
  # Optional
  description:
  # Cloud provider machine type
  instanceType: cpu=12,memory=32768,disk=100,type=SAN
  # Cloud provider image
  image: che01/UBUNTU_14_64
  # Total VMs to spin - Default is 1
  count: 1
  ', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, false,
  'Ubuntu 14 x64 32gb in Chennai', NULL);





INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version,
owner_pk, uuid, tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name,
version, yml, owner_id, tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description,
external_link) VALUES ('4028818650d4aca10150d4bf63470023', 'admin', '2015-12-08 15:05:24.039', 'admin', '2015-12-08 15:09:12.139', false,
false, 1, '402881834d9ee4d1014d9ee5d73f0014', '4028818650d4aca10150d4bf63470023', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER', 'OWNER',
'VM_COMPOSE', NULL, 'IBM SoftLayer Ubuntu 14 5XLarge TOK', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '1.0',
'# Note: Some of the fields are optional based on provider and can be left empty.
# 5XLarge - This is a user defined name.
5XLarge:
  # IBM SoftLayer region name Tokyo
  region: tok02
  # Optional
  description:
  # Cloud provider machine type
  instanceType: cpu=16,memory=49152,disk=100,type=SAN
  # Cloud provider image
  image: tok02/UBUNTU_14_64
  # Total VMs to spin - Default is 1
  count: 1
  ', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, false,
  'Ubuntu 14 x64 49gb in Tokyo', NULL);




INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk,
uuid, tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name, version, yml,
owner_id, tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description, external_link)
VALUES ('4028818650d4aca10150d4bf63470024', 'admin', '2015-12-08 15:05:24.039', 'admin', '2015-12-08 15:09:12.139', false, false, 1,
'402881834d9ee4d1014d9ee5d73f0014', '4028818650d4aca10150d4bf63470024', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER', 'OWNER',
'VM_COMPOSE', NULL, 'IBM SoftLayer Ubuntu 14 6XLarge MEL', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '1.0',
'# Note: Some of the fields are optional based on provider and can be left empty.
# 6XLarge - This is a user defined name.
6XLarge:
  # IBM SoftLayer region name Melbourne
  region: mel01
  # Optional
  description:
  # Cloud provider machine type
  instanceType: cpu=12,memory=65536,disk=100,type=SAN
  # Cloud provider image
  image: mel01/UBUNTU_14_64
  # Total VMs to spin - Default is 1
  count: 1
  ', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, false,
  'Ubuntu 14 x64 65gb in Melbourne', NULL);





-- 2x IBM SoftLayer Machines Compose Blueprints

INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk,
uuid, tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name, version,
yml, owner_id, tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description, external_link)
VALUES ('4028818650d4aca10150d4bf63470025', 'admin', '2015-12-08 15:05:24.039', 'admin', '2015-12-08 15:09:12.139', false, false, 1,
'402881834d9ee4d1014d9ee5d73f0014', '4028818650d4aca10150d4bf63470025', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER', 'OWNER',
'VM_COMPOSE', NULL, 'IBM SoftLayer Ubuntu 14 Small & Medium SJC', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '1.0',
'# Note: Some of the fields are optional based on provider and can be left empty.
# Small - This is a user defined name.
Small:
  # IBM SoftLayer region name San Jose
  region: sjc01
  # Optional
  description:
  # Cloud provider machine type
  instanceType: cpu=1,memory=1024,disk=25,type=SAN
  # Cloud provider image
  image: sjc01/UBUNTU_14_64
  # Total VMs to spin - Default is 1
  count: 1

# Medium - This is a user defined name.
Medium:
  # IBM SoftLayer region name San Jose
  region: sjc01
  # Optional
  description:
  # Cloud provider machine type
  instanceType: cpu=2,memory=2048,disk=25,type=SAN
  # Cloud provider image
  image: mel01/UBUNTU_14_64
  # Total VMs to spin - Default is 1
  count: 1
  ', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, false,
  'Ubuntu 14 x64 1&2gb in San Jose', NULL);




INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version,
owner_pk, uuid, tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name,
version, yml, owner_id, tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description,
external_link) VALUES ('4028818650d4aca10150d4bf63470026', 'admin', '2015-12-08 15:05:24.039', 'admin', '2015-12-08 15:09:12.139', false,
false, 1, '402881834d9ee4d1014d9ee5d73f0014', '4028818650d4aca10150d4bf63470026', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER', 'OWNER',
'VM_COMPOSE', NULL, 'IBM SoftLayer Ubuntu 14 Small & Large SJC/HOU', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '1.0',
'# Note: Some of the fields are optional based on provider and can be left empty.
# Small - This is a user defined name.
Small:
  # IBM SoftLayer region name San Jose
  region: sjc01
  # Optional
  description:
  # Cloud provider machine type
  instanceType: cpu=1,memory=1024,disk=25,type=SAN
  # Cloud provider image
  image: sjc01/UBUNTU_14_64
  # Total VMs to spin - Default is 1
  count: 1

# Medium - This is a user defined name.
Medium:
  # IBM SoftLayer region name Houston
  region: hou02
  # Optional
  description:
  # Cloud provider machine type
  instanceType: cpu=4,memory=4096,disk=100,type=SAN
  # Cloud provider image
  image: hou02/UBUNTU_14_64
  # Total VMs to spin - Default is 1
  count: 1
  ', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, false,
  'Ubuntu 14 x64 1&4gb in San Jose/Houston', NULL);



INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version,
owner_pk, uuid, tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name,
version, yml, owner_id, tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description,
external_link) VALUES ('4028818650d4aca10150d4bf63470027', 'admin', '2015-12-08 15:05:24.039', 'admin', '2015-12-08 15:09:12.139',
false, false, 1, '402881834d9ee4d1014d9ee5d73f0014', '4028818650d4aca10150d4bf63470027', '402881834d9ee4d1014d9ee5d73f0010',
'OWNER', 'OWNER', 'VM_COMPOSE', NULL, 'IBM SoftLayer Ubuntu 14 Medium & Large DAL', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '1.0',
'# Note: Some of the fields are optional based on provider and can be left empty.
# Medium - This is a user defined name.
Medium:
  # IBM SoftLayer region name Dallas
  region: dal01
  # Optional
  description:
  # Cloud provider machine type
  instanceType: cpu=2,memory=2048,disk=100,type=SAN
  # Cloud provider image
  image: dal01/UBUNTU_14_64
  # Total VMs to spin - Default is 1
  count: 1

# Large - This is a user defined name.
Large:
  # IBM SoftLayer region name Dallas
  region: dal01
  # Optional
  description:
  # Cloud provider machine type
  instanceType: cpu=4,memory=4096,disk=100,type=SAN
  # Cloud provider image
  image: dal01/UBUNTU_14_64
  # Total VMs to spin - Default is 1
  count: 1
  ', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, false,
  'Ubuntu 14 x64 2&4gb in Dallas', NULL);




INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk,
uuid, tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name, version, yml,
owner_id, tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description, external_link)
VALUES ('4028818650d4aca10150d4bf63470028', 'admin', '2015-12-08 15:05:24.039', 'admin', '2015-12-08 15:09:12.139', false, false, 1,
'402881834d9ee4d1014d9ee5d73f0014', '4028818650d4aca10150d4bf63470028', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER', 'OWNER',
'VM_COMPOSE', NULL, 'IBM SoftLayer Ubuntu 14 Large & XLarge DAL', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '1.0',
'# Note: Some of the fields are optional based on provider and can be left empty.
# Large - This is a user defined name.
Large:
  # IBM SoftLayer region name Dallas
  region: dal01
  # Optional
  description:
  # Cloud provider machine type
  instanceType: cpu=4,memory=4096,disk=100,type=SAN
  # Cloud provider image
  image: dal01/UBUNTU_14_64
  # Total VMs to spin - Default is 1
  count: 1

# XLarge - This is a user defined name.
XLarge:
  # IBM SoftLayer region name Dallas
  region: dal01
  # Optional
  description:
  # Cloud provider machine type
  instanceType: cpu=4,memory=8192,disk=100,type=SAN
  # Cloud provider image
  image: dal01/UBUNTU_14_64
  # Total VMs to spin - Default is 1
  count: 1
  ', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, false,
  'Ubuntu 14 x64 4&8gb in Dallas', NULL);





INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk,
uuid, tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name, version,
yml, owner_id, tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description, external_link)
VALUES ('4028818650d4aca10150d4bf63470029', 'admin', '2015-12-08 15:05:24.039', 'admin', '2015-12-08 15:09:12.139', false, false, 1,
'402881834d9ee4d1014d9ee5d73f0014', '4028818650d4aca10150d4bf63470029', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER', 'OWNER',
'VM_COMPOSE', NULL, 'IBM SoftLayer Ubuntu 14 3XLarge & 4XLarge LON/FRA', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '1.0',
'# Note: Some of the fields are optional based on provider and can be left empty.
# 3XLarge - This is a user defined name.
3XLarge:
  # IBM SoftLayer region name London
  region: lon02
  # Optional
  description:
  # Cloud provider machine type
  instanceType: cpu=8,memory=16384,disk=100,type=SAN
  # Cloud provider image
  image: lon02/UBUNTU_14_64
  # Total VMs to spin - Default is 1
  count: 1

# 4XLarge - This is a user defined name.
4XLarge:
  # IBM SoftLayer region name Frankfurt
  region: fra02
  # Optional
  description:
  # Cloud provider machine type
  instanceType: cpu=8,memory=32768,disk=100,type=SAN
  # Cloud provider image
  image: fra02/UBUNTU_14_64
  # Total VMs to spin - Default is 1
  count: 1
  ', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, false,
  'Ubuntu 14 x64 16&32gb in London/Frankfurt', NULL);


-- OpenStack Blueprint Sample

INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk,
uuid, tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name, version, yml,
owner_id, tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description, external_link)
VALUES ('4028818650d4aca10150d4bf63470101', 'admin', '2015-12-08 15:05:24.039', 'admin', '2015-12-08 15:09:12.139', false, false, 1,
'402881834d9ee4d1014d9ee5d73f0014', '4028818650d4aca10150d4bf63470101', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER', 'OWNER',
'VM_COMPOSE', NULL, 'OpenStack Ubuntu Large Regional Sample', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '1.0',
'# Note: Some of the fields are optional based on provider and can be left empty.
# Large - This is a user defined name.
Large:
  # OpenStack region name
  region: <<Region Name>>
  # System description
  description:
  # CPU Memory and Disk sizes
  instanceType: <<Memory Size>>
  # Image name
  image: <<Linux Image Name>>
  # Total VMs to spin - Default is 1
  count: 1
	  ', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, false,
    'OpenStack Ubuntu Large Regional Sample', NULL);



-- CloudStack Blueprint Sample

INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk,
uuid, tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name, version, yml,
owner_id, tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description, external_link)
VALUES ('4028818650d4aca10150d4bf63470102', 'admin', '2015-12-08 15:05:24.039', 'admin', '2015-12-08 15:09:12.139', false, false, 1,
'402881834d9ee4d1014d9ee5d73f0014', '4028818650d4aca10150d4bf63470102', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER', 'OWNER',
'VM_COMPOSE', NULL, 'CloudStack Ubuntu Large Regional Sample', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '1.0',
'# Note: Some of the fields are optional based on provider and can be left empty.
# Large - This is a user defined name.
Large:
  # CloudStack region name
  region:
  # System description
  description:
  # CPU Memory and Disk sizes
  instanceType: <<Memory Size>>
  # Image name
  image: <<Linux Image Name>>
  securityGroup: <<Default>>
  keyPair:
  username:
  password:
  # Total VMs to spin
  count: 1
  ', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, false,
  'CloudStack Ubuntu Large Regional Sample', NULL);



-- AWS Blueprint Sample

INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk,
  uuid, tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name, version, yml,
   owner_id, tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description, external_link)
    VALUES ('4028818650d4aca10150d4bf63470103', 'admin', '2015-12-08 15:05:24.039', 'admin', '2015-12-08 15:09:12.139', false, false, 1,
      '402881834d9ee4d1014d9ee5d73f0014', '4028818650d4aca10150d4bf63470103', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER', 'OWNER',
      'VM_COMPOSE', NULL, 'AWS RHEL Small 2gb Sample', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '1.0', 'Small:
  # AWS region name
  region: us-west-1
  # CPU & memory sizes
  instanceType: t2.small
  # Cloud provider image name
  image: us-west-1/ami-a540a5e1
  securityGroup: default
  keyPair: <<your-key-pair-name>>
  # use a seperate private-key for DCHQ provisioning
  # password should be actual private-key
  password: |
    -----BEGIN RSA PRIVATE KEY-----
    MIIEpAIBAAKCAQEAq3UZafpeiIKF13Y6nhAWIFUyttXlHccBzN+MOELTtiW1MbXIxoICEFQ6XVI+
    zJeECfVuxGMe9a1bTnWxiteHMUwhacBaE6Cgkofa60WTNx7lCUhfAYrLTpX2TfcOsfWvdiZcZGPc
    7alySVrX2t2e6MwlwHGhKfzDoM+wAH1/NNeISe1lcMUlxYUQr0CnMzmzuy6kuNY5l9GJSYp1kfcN
    CgId2c16EtHVswj6MzqN4eZBjB54sFhI6apnpLQF+aPYnFmkSiPL1cjreuKcXSHv31MAn7Evwudh
    iFp/47QFz8YutxQyyIdR9lzO+UsPaPYvyTIIosUx67BbgY1M7dT19QIDAQABAoIBAQCDxbuCd6mv
    wKCct4DVegeHUfNoEXvFJu17R1O86Z9Mni5jxhJIfiKX6iPxFQSuuMQB417+EC/7IQDLjCinTH3a
    pWgInoycCRdfhon5x78c3eGpALYDuWgHPbnOsdHZc7kmVNUtUpK5FUGAnxDGZQ18XWmmPa5fVqQD
    M5tQxSK+uKPB5KN2YnJu9wqD7bkCFdaeS9md2PX8Mh9c5PLENoLt5ZAYOZIHxaq3DV1cG3x4gPfF
    not52l9sN4dPmsJzktD9Irzs92A9K7UcPCzzsf20oUAWU1BULBtPJ1U5C6p7a18GIluaDwsX3Sj1
    7iSoOCmL3AF4+skZ+tS2zvbsW5EBAoGBAOaXtT4ecbAPk4ef2kl+i+SnbAXAxFwJRo+Yw+qJT77x
    HBIu7HPDhR7KLbzMU3Cnz4+PBMw3yKzGiFRW8lM8y8X8ueRyxs3k9G6NF/JGbUNqqXnd1miP0Lnp
    RJ22Xt5+/cx0iF5bc9fA/s/yY12Ncw2kEALjY9D0jkoyOJ4OUV97AoGBAL5ZX6YLtX0akkJ/qbc9
    38cBXnlZZgOB+miJZOCzOAVfmuosnnOZWCsNp2pn6j36q8GRpHvhHFSKdRYx7yV9oWbKn8eRfHpC
    Fz3IdWSPOsQBWooNjDAQ85ucJPyvmujTHH+b/iixfqIFWHZPU432XU9W35irjpU7GQwtpR8e2c1P
    AoGAAJEE1/c5LkaLhTzGS4HtFA25PTZhBRzcuIOWj5/wuzZabE0DNbGYRSAYBqu11MQExYSDbYEB
    lDMUqKhiBgRTN9RMHJyPknfqhc8A7Q5xPEYpzmvWGAGqcJp7GawWBZPoSbVLlHfDDBekSJx+0/8B
    9lmd1HP8jcncA6Cl9PVyC7sCgYddT2gwsQE4qBxtRexV+Jp2TlPRhm8HU7yZlP8NQyumeDxKBWll
    LrQ6wuwCgSK+5Jar3n+25T+/1kddiEeeBmv8cUrAYywBQd8ARj76JDMefz+9Zc5P6HHTvc2IYXzA
    HzVWibAcOTjz4/iRmHAOc5ZDTvsns3RMZfMYAnVpPlVgxQKBgQDjfESZP2V9wPloPCss/+TLX4dt
    WDTssM/qgoUeNiX3wMfGvVg6fsWK79C1FdxkGLmUIRfuHy6UyNhZvfCPOZ8ttIXETLYwgldWSUi3
    9qPPH8k3FooBHvtcLehE8XCi6TKrjf/pGxtR6h4yJDto729KZSFUBxtdObsdNJDHjxmXXA==
    -----END RSA PRIVATE KEY-----
  ', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, false,
  'AWS RHEL Small 2gb 1vCPU Sample', NULL);



INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk,
  uuid, tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name, version, yml,
  owner_id, tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description, external_link)
VALUES ('4028818650d4aca10150d4bf63470104', 'admin', '2015-12-08 15:05:24.039', 'admin', '2015-12-08 15:09:12.139', false, false, 1,
  '402881834d9ee4d1014d9ee5d73f0014', '4028818650d4aca10150d4bf63470104', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER', 'OWNER',
  'VM_COMPOSE', NULL, 'AWS RHEL Micro 1gb Sample', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '1.0', 'Micro:
  # AWS region name
  region: us-west-1
  # CPU & memory sizes
  instanceType: t2.micro
  # Cloud provider image name
  image: us-west-1/ami-a540a5e1
  securityGroup: default
  keyPair: <<your-key-pair-name>>
  # use a seperate private-key for DCHQ provisioning
  # password should be actual private-key
  password: |
    -----BEGIN RSA PRIVATE KEY-----
    MIIEpAIBAAKCAQEAq3UZafpeiIMF13Y6nhAWIFUyttXlHccBzN+MOELTtiW1MbXIxoICEFQ6XVI+
    zJeECfVuxGMe9a1bTnWxiteHMUwhacBaE6Cgkofa60WTNx7lCUhfAYrLTpX2TfcOsfWvdiZcZGPc
    7alySVrX2t2e6MwlwHGhKfzDoM+wAH1/NNeISe1lcMUlxYUQr0CnMzmzuy6kuNY5l9GJSYp1kfcN
    CgId2c16EtHVswj6MzqN4eZBjB54sFhI6apnpLQF+aPYnFmkSiPL1cjreuKcXSHv31MAn7Evwudh
    iFp/47QFz8YutxQyyIdR9lzO+UsPaPYvyTIIosUx67BbgY1M7dT19QIDAQABAoIBAQCDxbuCd6mv
    wKCct4DVegeHUfNoEXvFJu17R1O86Z9Mni5jxhJIfiKX6iPxFQSuuMQB417+EC/7IQDLjCinTH3a
    pWgInoycCRdfhon5x78c3eGpALYDuWgHPbnOsdHZc7kmVNUtUpK5FUGAnxDGZQ18XWmmPa5fVqQD
    M5tQxSK+uKPB5KN2YnJu9wqD7bkCFdaeS9md2PX8Mh9c5PLENoLt5ZAYOZIHxaq3DV1cG3x4gPfF
    not52l9sN4dPmsJzktD9Irzs92A9K7UcPCzzsf20oUAWU1BULBtPJ1U5C6p7a18GIluaDwsX3Sj1
    7iSoOCmL3AF4+skZ+tS2zvbsW5EBAoGBAOaXtT4ecbAPk4ef2kl+i+SnbAXAxFwJRo+Yw+qJT77x
    HBIu7HPDhR7KLbzMU3Cnz4+PBMw3yKzGiFRW8lM8y8X8ueRyxs3k9G6NF/JGbUNqqXnd1miP0Lnp
    RJ22Xt5+/cx0iF5bc9fA/s/yY12Ncw2kEALjY9D0jkoyOJ4OUV97AoGBAL5ZX6YLtX0akkJ/qbc9
    38cBXnlZZgOB+miJZOCzOAVfmuosnnOZWCsNp2pn6j36q8GRpHvhHFSKdRYx7yV9oWbKn8eRfHpC
    Fz3IdWSPOsQBWooNjDAQ85ucJPyvmujTHH+b/iixfqIFWHZPU432XU9W35irjpU7GQwtpR8e2c1P
    AoGAAJEE1/c5LkaLhTzGS4HtFA25PTZhBRzcuIOWj5/wuzZabE0DNbGYRSAYBqu11MQExYSDbYEB
    lDMUqKhiBgRTN9RMHJyPknfqhc8A7Q5xPEYpzmvWGAGqcJp7GawWBZPoSbVLlHfDDBekSJx+0/8B
    9lmd1HP8jcncA6Cl9PVyC7sCgYddT2gwsQE4qBxtRexV+Jp2TlPRhm8HU7yZlP8NQyumeDxKBWll
    LrQ6wuwCgSK+5Jar3n+25T+/1kddiEeeBmv8cUrAYywBQd8ARj76JDMefz+9Zc5P6HHTvc2IYXzA
    HzVWibAcOTjz4/iRmHAOc5ZDTvsns3RMZfMYAnVpPlVgxQKBgQDjfESZP2V9wPloPCss/+TLX4dt
    WDTssM/qgoUeNiX3wMfGvVg6fsWK79C1FdxkGLmUIRfuHy6UyNhZvfCPOZ8ttIXETLYwgldWSUi3
    9qPPH8k3FooBHvtcLehE8XCi6TKrjf/pGxtR6h4yJDto729KZSFUBxtdObsdNJDHjxmXXA==
    -----END RSA PRIVATE KEY-----
    ', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, false,
    'AWS RHEL Micro 1gb 1vCPU Sample', NULL);



INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version,
  owner_pk, uuid, tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name,
  version, yml, owner_id, tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description,
  external_link) VALUES ('4028818650d4aca10150d4bf63470105', 'admin', '2015-12-08 15:05:24.039', 'admin', '2015-12-08 15:09:12.139',
  false, false, 1, '402881834d9ee4d1014d9ee5d73f0014', '4028818650d4aca10150d4bf63470105', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER',
  'OWNER', 'VM_COMPOSE', NULL, 'AWS RHEL Medium 4gb Sample', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '1.0', 'Medium:
  # AWS region name
  region: us-west-1
  # CPU & memory sizes
  instanceType: t2.medium
  # Cloud provider image name
  image: us-west-1/ami-a540a5e1
  securityGroup: default
  keyPair: <<your-key-pair-name>>
  # use a seperate private-key for DCHQ provisioning
  # password should be actual private-key
  password: |
    -----BEGIN RSA PRIVATE KEY-----
    MIIEpAIBAAKCAQEAq3UZafpeiIMF13Y6nhAWIFUyttXlHccBzN+MOELTtiW1MbXIxoICEFQ6XVI+
    zJeECfVuxGMe9a1bTnWxiteHMUwhacBaE6Cgkofa60WTNx7lCUhfAYrLTpX2TfcOsfWvdiZcZGPc
    7alySVrX2t2e6MwlwHGhKfzDoM+wAH1/NNeISe1lcMUlxYUQr0CnMzmzuy6kuNY5l9GJSYp1kfcN
    CgId2c16EtHVswj6MzqN4eZBjB54sFhI6apnpLQF+aPYnFmkSiPL1cjreuKcXSHv31MAn7Evwudh
    iFp/47QFz8YutxQyyIdR9szO+UsPaPYvyTIIosUx67BbgY1M7dT19QIDAQABAoIBAQCDxbuCd6mv
    wKCct4DVegeHUfNoEXvFJu17R1O86Z9Mni5jxhJIfiKX6iPxFQSuuMQB417+EC/7IQDLjCinTH3a
    pWgInoycCRdfhon5x78c3eGpALYDuWgHPbnOsdHZc7kmVNUtUpK5FUGAnxDGZQ18XWmmPa5fVqQD
    M5tQxSK+uKPB5KN2YnJu9wqD7bkCFdaeS9md2PX8Mh9c5PLENoLt5ZAYOZIHxaq3DV1cG3x4gPfF
    not52l9sN4dPmsJzktD9Irzs92A9K7UcPCzzsf20oUAWU1BULBtPJ1U5C6p7a18GIluaDwsX3Sj1
    7iSoOCmL3AF4+skZ+tS2zvbsW5EBAoGBAOaXtT4ecbAPk4ef2kl+i+SnbAXAxFwJRo+Yw+qJT77x
    HBIu7HPDhR7KLbzMU3Cnz4+PBMw3yKzGiFRW8lM8y8X8ueRyxs3k9G6NF/JGbUNqqXnd1miP0Lnp
    RJ22Xt5+/cx0iF5bc9fA/s/yY12Ncw2kEALjY9D0jkoyOJ4OUV97AoGBAL5ZX6YLtX0akkJ/qbc9
    38cBXnlZZgOB+miJZOCzOAVfmuosnnOZWCsNp2pn6j36q8GRpHvhHFSKdRYx7yV9oWbKn8eRfHpC
    Fz3IdWSPOsQBWooNjDAQ85ucJPyvmujTHH+b/iixfqIFWHZPU432XU9W35irjpU7GQwtpR8e2c1P
    AoGAAJEE1/c5LkaLhTzGS4HtFA25PTZhBRzcuIOWj5/wuzZabE0DNbGYRSAYBqu11MQExYSDbYEB
    lDMUqKhiBgRTN9RMHJyPknfqhc8A7Q5xPEYpzmvWGAGqcJp7GawWBZPoSbVLlHfDDBekSJx+0/8B
    9lmd1HP8jcncA6Cl9PVyC7sCgYddT2gwsQE4qBxtRexV+Jp2TlPRhm8HU7yZlP8NQyumeDxKBWll
    LrQ6wuwCgSK+5Jar3n+25T+/1kddiEeeBmv8cUrAYywBQd8ARj76JDMefz+9Zc5P6HHTvc2IYXzA
    HzVWibAcOTjz4/iRmHAOc5ZDTvsns3RMZfMYAnVpPlVgxQKBgQDjfESZP2V9wPloPCss/+TLX4dt
    WDTssM/qgoUeNiX3wMfGvVg6fsWK79C1FdxkGLmUIRfuHy6UyNhZvfCPOZ8ttIXETLYwgldWSUi3
    9qPPH8k3FooBHvtcLehE8XCi6TKrjf/pGxtR6h4yJDto729KZSFUBxtdObsdNJDHjxmXXA==
    -----END RSA PRIVATE KEY-----
    ', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, false,
    'AWS RHEL Medium 4gb 2vCPU Sample', NULL);






INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk,
  uuid, tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name, version, yml,
  owner_id, tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description, external_link)
VALUES ('4028818650d4aca10150d4bf63470106', 'admin', '2015-12-08 15:05:24.039', 'admin', '2015-12-08 15:09:12.139', false, false, 1,
 '402881834d9ee4d1014d9ee5d73f0014', '4028818650d4aca10150d4bf63470106', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER', 'OWNER',
 'VM_COMPOSE', NULL, 'AWS RHEL Large 8gb Sample', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '1.0', 'Large:
  # AWS region name
  region: us-west-1
  # CPU & memory sizes
  instanceType: t2.large
  # Cloud provider image name
  image: us-west-1/ami-a540a5e1
  securityGroup: default
  keyPair: <<your-key-pair-name>>
  # use a seperate private-key for DCHQ provisioning
   # password should be actual private-key
  password: |
  -----BEGIN RSA PRIVATE KEY-----
    MIIEpAIBAAKCAQEAq3UZafpeiIMF13Y6nhAWIFUyttXlHccBzN+MOELTtiW1MbXIxoICEFQ6XVI+
    zJeECfVuxGMe9a1bTnWxiteHMUwhacBaE6Cgkofa60WTNx7lCUhfAYrLTpX2TfcOsfWvdiZcZGPc
    7alySVrX2t2e6MwlwHGhKfzDoM+wAH1/NNeISe1lcMUlxYUQr0CnMzmzuy6kuNY5l9GJSYp1kfcN
    CgId2c16EtHVswj6MzqN4eZBjB54sFhI6apnpLQF+aPYnFmkSiPL1cjreuKcXSHv31MAn7Evwudh
    iFp/47QFz8YutxQyyIdR9szO+UsMaPYvyTIIosUx67BbgY1M7dT19QIDAQABAoIBAQCDxbuCd6mv
    wKCct4DVegeHUfNoEXvFJu17R1O86Z9Mni5jxhJIfiKX6iPxFQSuuMQB417+EC/7IQDLjCinTH3a
    pWgInoycCRdfhon5x78c3eGpALYDuWgHPbnOsdHZc7kmVNUtUpK5FUGAnxDGZQ18XWmmPa5fVqQD
    M5tQxSK+uKPB5KN2YnJu9wqD7bkCFdaeS9md2PX8Mh9c5PLENoLt5ZAYOZIHxaq3DV1cG3x4gPfF
    not52l9sN4dPmsJzktD9Irzs92A9K7UcPCzzsf20oUAWU1BULBtPJ1U5C6p7a18GIluaDwsX3Sj1
    7iSoOCmL3AF4+skZ+tS2zvbsW5EBAoGBAOaXtT4ecbAPk4ef2kl+i+SnbAXAxFwJRo+Yw+qJT77x
    HBIu7HPDhR7KLbzMU3Cnz4+PBMw3yKzGiFRW8lM8y8X8ueRyxs3k9G6NF/JGbUNqqXnd1miP0Lnp
    RJ22Xt5+/cx0iF5bc9fA/s/yY12Ncw2kEALjY9D0jkoyOJ4OUV97AoGBAL5ZX6YLtX0akkJ/qbc9
    38cBXnlZZgOB+miJZOCzOAVfmuosnnOZWCsNp2pn6j36q8GRpHvhHFSKdRYx7yV9oWbKn8eRfHpC
    Fz3IdWSPOsQBWooNjDAQ85ucJPyvmujTHH+b/iixfqIFWHZPU432XU9W35irjpU7GQwtpR8e2c1P
    AoGAAJEE1/c5LkaLhTzGS4HtFA25PTZhBRzcuIOWj5/wuzZabE0DNbGYRSAYBqu11MQExYSDbYEB
    lDMUqKhiBgRTN9RMHJyPknfqhc8A7Q5xPEYpzmvWGAGqcJp7GawWBZPoSbVLlHfDDBekSJx+0/8B
    9lmd1HP8jcncA6Cl9PVyC7sCgYddT2gwsQE4qBxtRexV+Jp2TlPRhm8HU7yZlP8NQyumeDxKBWll
    LrQ6wuwCgSK+5Jar3n+25T+/1kddiEeeBmv8cUrAYywBQd8ARj76JDMefz+9Zc5P6HHTvc2IYXzA
    HzVWibAcOTjz4/iRmHAOc5ZDTvsns3RMZfMYAnVpPlVgxQKBgQDjfESZP2V9wPloPCss/+TLX4dt
    WDTssM/qgoUeNiX3wMfGvVg6fsWK79C1FdxkGLmUIRfuHy6UyNhZvfCPOZ8ttIXETLYwgldWSUi3
    9qPPH8k3FooBHvtcLehE8XCi6TKrjf/pGxtR6h4yJDto729KZSFUBxtdObsdNJDHjxmXXA==
    -----END RSA PRIVATE KEY-----
    ', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, false,
    'AWS RHEL Large 8gb 2vCPU Sample', NULL);





INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version,
  owner_pk, uuid, tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name,
  version, yml, owner_id, tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description,
  external_link) VALUES ('4028818650d4aca10150d4bf63470107', 'admin', '2015-12-08 15:05:24.039', 'admin', '2015-12-08 15:09:12.139',
  false, false, 1, '402881834d9ee4d1014d9ee5d73f0014', '4028818650d4aca10150d4bf63470107', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER',
  'OWNER', 'VM_COMPOSE', NULL, 'AWS RHEL XLarge 16gb Sample', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '1.0', 'XLarge:
  # AWS region name
  region: us-west-1
  # CPU & memory sizes
  instanceType: m4.xlarge
  # Cloud provider image name
  image: us-west-1/ami-a540a5e1
  securityGroup: default
  keyPair: <<your-key-pair-name>>
  # use a seperate private-key for DCHQ provisioning
  # password should be actual private-key
  password: |
  -----BEGIN RSA PRIVATE KEY-----
    MIIEpAIBAAKCAQEAq3UZafpeiIMF13Y6nhAWIFUyttXlHccBzN+MOELTtiW1MbXIxoICEFQ6XVI+
    zJeECfVuxGMe9a1bTnWxiteHMUwhacBaE6Cgkofa10WTNx7lCUhfAYrLTpX2TfcOsfWvdiZcZGPc
    7alySVrX2t2e6MwlwHGhKfzDoM+wAH1/NNeISe1lcMUlxYUQr0CmMzmzuy6kuNY5l9GJSYp1kfcN
    CgId2c16EtHVswj6MzqN4eZBjB54sFhI6apnpLQF+aPYnFmkSiPL1cjreuKcXSHv31MAn7Evwudh
    iFp/47QFz8YutxQyyIdR9szO+UsMaPYvyTIIosUx67BbgY1M7dT19QIDAQABAoIBAQCDxbuCd6mv
    wKCct4DVegeHUfNoEXvFJu17R1O86Z9Mni5jxhJIfiKX6iPxFQSuuMQB417+EC/7IQDLjCinTH3a
    pWgInoycCRdfhon5x78c3eGpALYDuWgHPbnOsdHZc7kmVNUtUpK5FUGAnxDGZQ18XWmmPa5fVqQD
    M5tQxSK+uKPB5KN2YnJu9wqD7bkCFdaeS9md2PX8Mh9c5PLENoLt5ZAYOZIHxaq3DV1cG3x4gPfF
    not52l9sN4dPmsJzktD9Irzs92A9K7UcPCzzsf20oUAWU1BULBtPJ1U5C6p7a18GIluaDwsX3Sj1
    7iSoOCmL3AF4+skZ+tS2zvbsW5EBAoGBAOaXtT4ecbAPk4ef2kl+i+SnbAXAxFwJRo+Yw+qJT77x
    HBIu7HPDhR7KLbzMU3Cnz4+PBMw3yKzGiFRW8lM8y8X8ueRyxs3k9G6NF/JGbUNqqXnd1miP0Lnp
    RJ22Xt5+/cx0iF5bc9fA/s/yY12Ncw2kEALjY9D0jkoyOJ4OUV97AoGBAL5ZX6YLtX0akkJ/qbc9
    38cBXnlZZgOB+miJZOCzOAVfmuosnnOZWCsNp2pn6j36q8GRpHvhHFSKdRYx7yV9oWbKn8eRfHpC
    Fz3IdWSPOsQBWooNjDAQ85ucJPyvmujTHH+b/iixfqIFWHZPU432XU9W35irjpU7GQwtpR8e2c1P
    AoGAAJEE1/c5LkaLhTzGS4HtFA25PTZhBRzcuIOWj5/wuzZabE0DNbGYRSAYBqu11MQExYSDbYEB
    lDMUqKhiBgRTN9RMHJyPknfqhc8A7Q5xPEYpzmvWGAGqcJp7GawWBZPoSbVLlHfDDBekSJx+0/8B
    9lmd1HP8jcncA6Cl9PVyC7sCgYddT2gwsQE4qBxtRexV+Jp2TlPRhm8HU7yZlP8NQyumeDxKBWll
    LrQ6wuwCgSK+5Jar3n+25T+/1kddiEeeBmv8cUrAYywBQd8ARj76JDMefz+9Zc5P6HHTvc2IYXzA
    HzVWibAcOTjz4/iRmHAOc5ZDTvsns3RMZfMYAnVpPlVgxQKBgQDjfESZP2V9wPloPCss/+TLX4dt
    WDTssM/qgoUeNiX3wMfGvVg6fsWK79C1FdxkGLmUIRfuHy6UyNhZvfCPOZ8ttIXETLYwgldWSUi3
    9qPPH8k3FooBHvtcLehE8XCi6TKrjf/pGxtR6h4yJDto729KZSFUBxtdObsdNJDHjxmXXA==
    -----END RSA PRIVATE KEY-----
    ', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, false,
    'AWS RHEL XLarge 16gb 4vCPU Sample', NULL);




INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version,
  owner_pk, uuid, tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name,
  version, yml, owner_id, tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description,
  external_link) VALUES ('4028818650d4aca10150d4bf63470108', 'admin', '2015-12-08 15:05:24.039', 'admin', '2015-12-08 15:09:12.139',
  false, false, 1, '402881834d9ee4d1014d9ee5d73f0014', '4028818650d4aca10150d4bf63470108', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER',
  'OWNER', 'VM_COMPOSE', NULL, 'AWS RHEL 2XLarge 32gb Sample', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '1.0', '2XLarge:
  # AWS region name
  region: us-west-1
  # CPU & memory sizes
  instanceType: m4.2xlarge
  # Cloud provider image name
  image: us-west-1/ami-a540a5e1
  securityGroup: default
  keyPair: <<your-key-pair-name>>
  # use a seperate private-key for DCHQ provisioning
  # password should be actual private-key
  password: |
  -----BEGIN RSA PRIVATE KEY-----
    MIIEpAIBAAKCAQEAq3UZafpeiIMF13Y6nhAWIFUyttXlHccBzN+MOELTtiW1MbXIxoICEFQ6XVI+
    zJeECfVuxGMe9a1bTnWxiteHMUwhacBaE6Cgkofa10WTNx7lCUhfAYrLTpX2TfcOsfWvdiZcZGPc
    7alySVrX2t2e6MwlwHGhKfzDoM+wAH1/NNeISm1lcMUlxYUQr0CmMzmzuy6kuNY5l9GJSYp1kfcN
    CgId2c16EtHVswj6MzqN4eZBjB54sFhI6apnpLQF+aPYnFmkSiPL1cjreuKcXSHv31MAn7Evwudh
    iFp/47QFz8YutxQyyIdR9szO+UsMaPYvyTIIosUx67BbgY1M7dT19QIDAQABAoIBAQCDxbuCd6mv
    wKCct4DVegeHUfNoEXvFJu17R1O86Z9Mni5jxhJIfiKX6iPxFQSuuMQB417+EC/7IQDLjCinTH3a
    pWgInoycCRdfhon5x78c3eGpALYDuWgHPbnOsdHZc7kmVNUtUpK5FUGAnxDGZQ18XWmmPa5fVqQD
    M5tQxSK+uKPB5KN2YnJu9wqD7bkCFdaeS9md2PX8Mh9c5PLENoLt5ZAYOZIHxaq3DV1cG3x4gPfF
    not52l9sN4dPmsJzktD9Irzs92A9K7UcPCzzsf20oUAWU1BULBtPJ1U5C6p7a18GIluaDwsX3Sj1
    7iSoOCmL3AF4+skZ+tS2zvbsW5EBAoGBAOaXtT4ecbAPk4ef2kl+i+SnbAXAxFwJRo+Yw+qJT77x
    HBIu7HPDhR7KLbzMU3Cnz4+PBMw3yKzGiFRW8lM8y8X8ueRyxs3k9G6NF/JGbUNqqXnd1miP0Lnp
    RJ22Xt5+/cx0iF5bc9fA/s/yY12Ncw2kEALjY9D0jkoyOJ4OUV97AoGBAL5ZX6YLtX0akkJ/qbc9
    38cBXnlZZgOB+miJZOCzOAVfmuosnnOZWCsNp2pn6j36q8GRpHvhHFSKdRYx7yV9oWbKn8eRfHpC
    Fz3IdWSPOsQBWooNjDAQ85ucJPyvmujTHH+b/iixfqIFWHZPU432XU9W35irjpU7GQwtpR8e2c1P
    AoGAAJEE1/c5LkaLhTzGS4HtFA25PTZhBRzcuIOWj5/wuzZabE0DNbGYRSAYBqu11MQExYSDbYEB
    lDMUqKhiBgRTN9RMHJyPknfqhc8A7Q5xPEYpzmvWGAGqcJp7GawWBZPoSbVLlHfDDBekSJx+0/8B
    9lmd1HP8jcncA6Cl9PVyC7sCgYddT2gwsQE4qBxtRexV+Jp2TlPRhm8HU7yZlP8NQyumeDxKBWll
    LrQ6wuwCgSK+5Jar3n+25T+/1kddiEeeBmv8cUrAYywBQd8ARj76JDMefz+9Zc5P6HHTvc2IYXzA
    HzVWibAcOTjz4/iRmHAOc5ZDTvsns3RMZfMYAnVpPlVgxQKBgQDjfESZP2V9wPloPCss/+TLX4dt
    WDTssM/qgoUeNiX3wMfGvVg6fsWK79C1FdxkGLmUIRfuHy6UyNhZvfCPOZ8ttIXETLYwgldWSUi3
    9qPPH8k3FooBHvtcLehE8XCi6TKrjf/pGxtR6h4yJDto729KZSFUBxtdObsdNJDHjxmXXA==
    -----END RSA PRIVATE KEY-----
    ', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, false,
    'AWS RHEL 2XLarge 32gb 8vCPU Sample', NULL);