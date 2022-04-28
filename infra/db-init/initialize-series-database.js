db = db.getSiblingDB('series_database');

db.createUser({
    user: 'superuser',
    pwd: 'password',
    roles: [
        {
            role: 'dbOwner',
            db: 'series_database'
        }
    ]
})

db.createUser({
    user: 'series',
    pwd: 'password',
    roles: [
        {
            role: 'readWrite',
            db: 'series_database'
        }
    ]
})