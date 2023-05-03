-- author Faizan Mohammad
-- Update machine_type for GCP

update machine_type set total_disk = null
	WHERE cloud = 'GOOGLE_COMPUTE_ENGINE' and total_disk > 0;
