db = db.getSiblingDB('catalog_database');

db.createUser({
    user: 'superuser',
    pwd: 'password',
    roles: [
        {
            role: 'dbOwner',
            db: 'catalog_database'
        }
    ]
})

db.createUser({
    user: 'catalog',
    pwd: 'password',
    roles: [
        {
            role: 'readWrite',
            db: 'catalog_database'
        }
    ]
})