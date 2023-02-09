# Useful Scripts
* `baseData.sql` adds 3 users with different roles to test with. Useful for working from a blank database in dev.

| email           | pwd   |
|-----------------|-------|
| test@test.com   | test  |
| demo@demo.com   | demo  |
| admin@admin.com | admin |

```
psql -U comicadmin -d comicdb -a -f scripts/database/baseData.sql
```