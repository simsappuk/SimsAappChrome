-- Table: container_envs, image_envs, plugin_envs, plugin_profile_envs

-- UPDATE val, eval

ALTER TABLE container_envs ALTER COLUMN val TYPE TEXT;
ALTER TABLE container_envs ALTER COLUMN eval TYPE TEXT;

ALTER TABLE image_envs ALTER COLUMN val TYPE TEXT;
ALTER TABLE image_envs ALTER COLUMN eval TYPE TEXT;

ALTER TABLE plugin_envs ALTER COLUMN val TYPE TEXT;
ALTER TABLE plugin_envs ALTER COLUMN eval TYPE TEXT;

ALTER TABLE plugin_profile_envs ALTER COLUMN val TYPE TEXT;
ALTER TABLE plugin_profile_envs ALTER COLUMN eval TYPE TEXT;