function fn() {
if (!env) {
env = 'QA';
}
var config = {
// variables & api paths
}
//'karate.env' - Get system property
var env = karate.env;
karate.log('karate.env system property was:', env);
if (env == 'test') {
config.baseUrl = 'https://restcountries.com';
} else if (env == 'staging') {
config.baseUrl = 'https://restcountries.com';
}
karate.configure('connectTimeout', 5000);
karate.configure('readTimeout', 5000);
return config;
}