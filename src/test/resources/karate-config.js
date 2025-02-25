function fn() {
  var env = karate.env; // get system property 'karate.env'
  karate.log('karate.env system property was:', env);
  if (!env) {
    env = 'dev';
  }
  var config = {
    env: env,
    myVarName: 'someValue'
  }
  if (env == 'staging') {
    // customize
    // e.g. config.foo = 'bar';
  } else if (env == 'PRO') {
    // customize
  }
  return config;
}