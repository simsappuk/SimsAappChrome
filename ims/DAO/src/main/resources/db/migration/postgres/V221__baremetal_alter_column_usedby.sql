
-- Author: Faizan Mohammad

UPDATE baremetal_nodes
	SET status='AVAILABLE', used_by_user=null
	WHERE used_by_user IS NOT NULL;

alter table baremetal_nodes rename column used_by_user to used_by_user_id;

alter table baremetal_nodes Add CONSTRAINT fk_used_by_user_id FOREIGN KEY (used_by_user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION;