-- Author : Faizan Mohammad

ALTER TABLE docker_volume
    ADD COLUMN delete_on_termination boolean DEFAULT FALSE;