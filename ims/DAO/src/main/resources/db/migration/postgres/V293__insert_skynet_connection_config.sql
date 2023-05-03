INSERT INTO app_config
(id, key_, description)
VALUES
('skynet.registration-date','skynet.registration-date','skynet registration date');

INSERT INTO app_config
(id, key_, description)
VALUES
('hcp_id','hcp_id','unique hcp id');

INSERT INTO app_config
(id, key_, description)
VALUES
('skynet.eula.accepted','skynet.eula.accepted','skynet eula acceptance');

update app_config
SET field_type = 'hidden'
WHERE id IN
(
    'skynet.registration-date',
    'hcp_id',
    'skynet.user',
    'skynet.password'
);

update app_config
SET field_type = 'readonly'
WHERE id IN
('skynet.url');

update app_config
SET field_type = 'readonly'
WHERE id IN
('skynet.eula.accepted');
