# Useful Scripts
* `baseData.sql` adds 3 users with different roles to test with. Useful for working from a blank database in dev.
```
psql -U comicadmin -d comicdb -a -f scripts/database/baseData.sql
```