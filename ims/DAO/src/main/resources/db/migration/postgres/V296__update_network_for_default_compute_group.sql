--UDPATE default compute group to docker.local
UPDATE data_center SET network = 'docker.local' where name LIKE 'Compute-Group (%' AND network = 'docker.skip';
