
-- Author:  Mohammed Luqman Shareef

alter table baremetal_nodes rename column reserved to reserved_by_id;

alter table baremetal_nodes Add CONSTRAINT fk_reserved_by_id FOREIGN KEY (reserved_by_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION;