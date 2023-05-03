-- author Intesar Mohammed
-- Sample Aliyun Machine Compose Blueprints
-- Blueprint demonstrates how to create a blueprint with support for volumes, tags, plugins, region & security-group

INSERT INTO blueprint (id, created_by, created_date, last_modified_by, last_modified_date, deleted, inactive, lock_version, owner_pk, uuid,
tenant_pk, entitlement, entitlement_type, blueprint_type, description, name, total_run, total_stars, user_name, version, yml, owner_id,
tenant_id, image_id, git_checksum, git_id, git_url, git_public_visible, editable, short_description, external_link)
VALUES ('4028818650d4aca10150d4bf63470001ali', 'admin', '2016-04-02 15:05:24.039', 'admin', '2016-04-02 15:09:12.139', false, false, 1, '402881834d9ee4d1014d9ee5d73f0014',
'4028818650d4aca10150d4bf63470001ali', '402881834d9ee4d1014d9ee5d73f0010', 'OWNER', 'OWNER', 'VM_COMPOSE', NULL,
'Aliyun Ubuntu 14.04 Large US-WEST-1 (Sample)', 1, 1, '402881834d9ee4d1014d9ee5d73f0014', '1.0',
'
# Large - Instance name prefix.
Large:
  region: us-west-1
  instanceType: ecs.s2.large
  # Ubuntu 14.04 64bit
  image: ubuntu1404_64_40G_aliaegis_20160222.vhd
  # mandatory - port 22 should be accessible
  securityGroup: sg-u11e46tlb
  # SSH ROOT username
  username: root
  # SSH ROOT password
  password: Apple123
  # Optionally attach additional disks
  volumes:
    - !volume
      size: 100
      deleteOnTerminate: true
    - !volume
      size: 150
      deleteOnTerminate: true
  # Optionally add tags
  tags:
    platform: dchq
    env: test
  # Optionally execute scripts post provision.
  #plugins:
  #  - !plugin
  #    id: sx6o3

  # VM count
  count: 1

  ', '402881834d9ee4d1014d9ee5d73f0014', '402881834d9ee4d1014d9ee5d73f0010', NULL, NULL, NULL, NULL, NULL, false,
  'Ubuntu 14.04 x64 4gb in US-WEST-1', NULL);
