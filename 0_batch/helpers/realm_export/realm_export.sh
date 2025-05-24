#!/bin/sh
rm -rf /tmp/export
mkdir /tmp/export
cp -rp /opt/keycloak/data/h2 /tmp/export
/opt/keycloak/bin/kc.sh export \
  --dir /tmp/export \
  --users same_file \
  --realm quarkus \
  --db dev-file \
  --db-url 'jdbc:h2:file:/tmp/export/h2/keycloakdb;NON_KEYWORDS=VALUE'