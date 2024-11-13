--liquibase formatted sql

--changeset gsuleimanov:8
-- Add location column to expenses table
ALTER TABLE expenses ADD COLUMN location VARCHAR(255);
--rollback ALTER TABLE expenses DROP COLUMN location;

--changeset gsuleimanov:9
-- Add index for location searches
CREATE INDEX idx_expenses_location ON expenses(location);
--rollback DROP INDEX idx_expenses_location;