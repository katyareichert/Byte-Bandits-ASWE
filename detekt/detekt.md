# detekt

## Metrics

* 177 number of properties

* 65 number of functions

* 15 number of classes

* 9 number of packages

* 17 number of kt files

## Complexity Report

* 1,142 lines of code (loc)

* 889 source lines of code (sloc)

* 638 logical lines of code (lloc)

* 66 comment lines of code (cloc)

* 129 cyclomatic complexity (mcc)

* 87 cognitive complexity

* 74 number of total code smells

* 7% comment source ratio

* 202 mcc per 1,000 lloc

* 115 code smells per 1,000 lloc

## Findings (74)

### complexity, LongMethod (1)

One method should have one responsibility. Long methods tend to handle many things at once. Prefer smaller methods to make them easier to understand.

[Documentation](https://detekt.dev/docs/rules/complexity#longmethod)

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/plugins/Routing.kt:22:17
```
The function configureRouting is too long (92). The maximum length is 60.
```
```kotlin
19 
20 
21 //TODO : move the routes into their own classes files
22 fun Application.configureRouting() {
!!                 ^ error
23 	routing {
24 		//This addds authentication, the routes are now protected
25 		authenticate {

```

### exceptions, SwallowedException (8)

The caught exception is swallowed. The original exception could be lost.

[Documentation](https://detekt.dev/docs/rules/exceptions#swallowedexception)

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/encryption/Encryption.kt:19:16
```
The caught exception is swallowed. The original exception could be lost.
```
```kotlin
16 
17       try {
18         secretKey = validateAndPrepareKey(key)
19       } catch (e: IllegalArgumentException) {
!!                ^ error
20         throw IllegalArgumentException("Invalid key: ${e.message}")
21       }
22 

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/encryption/Encryption.kt:42:16
```
The caught exception is swallowed. The original exception could be lost.
```
```kotlin
39 
40       try {
41         secretKey = validateAndPrepareKey(key)
42       } catch (e: IllegalArgumentException) {
!!                ^ error
43         throw IllegalArgumentException("Invalid key: ${e.message}")
44       }
45 

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/encryption/Encryption.kt:50:16
```
The caught exception is swallowed. The original exception could be lost.
```
```kotlin
47       cipher.init(Cipher.DECRYPT_MODE, secretKey)
48       try {
49         return cipher.doFinal(contents)
50       } catch (e: Exception) {
!!                ^ error
51         throw IllegalArgumentException("Invalid key: ${e.message}")
52       }
53     }

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/plugins/Routing.kt:57:15
```
The caught exception is swallowed. The original exception could be lost.
```
```kotlin
54 
55 						val responseText = FilePersister.simpleFileRetrieve(fileName, "storage", clientId, userId)
56 						call.respondText(status = HttpStatusCode.OK) { responseText.toString(Charsets.UTF_8) }
57 					} catch (e: Exception) {
!!               ^ error
58 						call.respondText(status = HttpStatusCode.BadGateway, provider = { "This had an error" })
59 					}
60 				}

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/plugins/Routing.kt:74:15
```
The caught exception is swallowed. The original exception could be lost.
```
```kotlin
71 
72 						val responseText = FilePersister.simpleFileDelete(fileName, "storage", clientId, userId)
73 						call.respondText(status = HttpStatusCode.OK) { responseText.toString() }
74 					} catch (e: Exception) {
!!               ^ error
75 						call.respondText(status = HttpStatusCode.BadGateway, provider = { "This had an error" })
76 					}
77 				}

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/plugins/Routing.kt:91:14
```
The caught exception is swallowed. The original exception could be lost.
```
```kotlin
88 					val pwd = PassKeyGeneration.passwordGen(len, digits, casing, specialChars)
89 
90 					call.respondText(status = HttpStatusCode.OK) { pwd }
91 				} catch (e: Exception) {
!!              ^ error
92 					call.respondText(status = HttpStatusCode.BadGateway, provider = { "This had an error" })
93 				}
94 			}

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/plugins/Routing.kt:106:14
```
The caught exception is swallowed. The original exception could be lost.
```
```kotlin
103 
104 					val responseText = PassKeyGeneration.passkeyGen(len, password, salt)
105 					call.respondText(status = HttpStatusCode.OK) { responseText.toString(Charsets.UTF_8) }
106 				} catch (e: Exception) {
!!!              ^ error
107 					call.respondText(status = HttpStatusCode.BadGateway, provider = { "This had an error" })
108 				}
109 			}

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/plugins/Routing.kt:120:14
```
The caught exception is swallowed. The original exception could be lost.
```
```kotlin
117 
118 					//val responseText = FilePersister.simpleFileRetrieve(user, recordKey)
119 					call.respondText(status = HttpStatusCode.OK) { "responseText" }
120 				} catch (e: Exception) {
!!!              ^ error
121 					call.respondText(status = HttpStatusCode.BadGateway, provider = { "This had an error" })
122 				}
123 			}

```

### exceptions, TooGenericExceptionCaught (7)

The caught exception is too generic. Prefer catching specific exceptions to the case that is currently handled.

[Documentation](https://detekt.dev/docs/rules/exceptions#toogenericexceptioncaught)

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/encryption/Encryption.kt:50:16
```
The caught exception is too generic. Prefer catching specific exceptions to the case that is currently handled.
```
```kotlin
47       cipher.init(Cipher.DECRYPT_MODE, secretKey)
48       try {
49         return cipher.doFinal(contents)
50       } catch (e: Exception) {
!!                ^ error
51         throw IllegalArgumentException("Invalid key: ${e.message}")
52       }
53     }

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/plugins/Routing.kt:40:15
```
The caught exception is too generic. Prefer catching specific exceptions to the case that is currently handled.
```
```kotlin
37 						val clientId = principal!!.payload.getClaim("clientId").asString()
38 						val saved = FilePersister.simpleFilePersist(request, "storage", clientId, true)
39 						call.respondText(status = HttpStatusCode.OK) { "This worked: $saved" }
40 					} catch (e: Exception) {
!!               ^ error
41 						logger.error("error serving request", e)
42 						call.respondText(status = HttpStatusCode.BadGateway, provider = { "This had an error" })
43 					}

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/plugins/Routing.kt:57:15
```
The caught exception is too generic. Prefer catching specific exceptions to the case that is currently handled.
```
```kotlin
54 
55 						val responseText = FilePersister.simpleFileRetrieve(fileName, "storage", clientId, userId)
56 						call.respondText(status = HttpStatusCode.OK) { responseText.toString(Charsets.UTF_8) }
57 					} catch (e: Exception) {
!!               ^ error
58 						call.respondText(status = HttpStatusCode.BadGateway, provider = { "This had an error" })
59 					}
60 				}

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/plugins/Routing.kt:74:15
```
The caught exception is too generic. Prefer catching specific exceptions to the case that is currently handled.
```
```kotlin
71 
72 						val responseText = FilePersister.simpleFileDelete(fileName, "storage", clientId, userId)
73 						call.respondText(status = HttpStatusCode.OK) { responseText.toString() }
74 					} catch (e: Exception) {
!!               ^ error
75 						call.respondText(status = HttpStatusCode.BadGateway, provider = { "This had an error" })
76 					}
77 				}

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/plugins/Routing.kt:91:14
```
The caught exception is too generic. Prefer catching specific exceptions to the case that is currently handled.
```
```kotlin
88 					val pwd = PassKeyGeneration.passwordGen(len, digits, casing, specialChars)
89 
90 					call.respondText(status = HttpStatusCode.OK) { pwd }
91 				} catch (e: Exception) {
!!              ^ error
92 					call.respondText(status = HttpStatusCode.BadGateway, provider = { "This had an error" })
93 				}
94 			}

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/plugins/Routing.kt:106:14
```
The caught exception is too generic. Prefer catching specific exceptions to the case that is currently handled.
```
```kotlin
103 
104 					val responseText = PassKeyGeneration.passkeyGen(len, password, salt)
105 					call.respondText(status = HttpStatusCode.OK) { responseText.toString(Charsets.UTF_8) }
106 				} catch (e: Exception) {
!!!              ^ error
107 					call.respondText(status = HttpStatusCode.BadGateway, provider = { "This had an error" })
108 				}
109 			}

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/plugins/Routing.kt:120:14
```
The caught exception is too generic. Prefer catching specific exceptions to the case that is currently handled.
```
```kotlin
117 
118 					//val responseText = FilePersister.simpleFileRetrieve(user, recordKey)
119 					call.respondText(status = HttpStatusCode.OK) { "responseText" }
120 				} catch (e: Exception) {
!!!              ^ error
121 					call.respondText(status = HttpStatusCode.BadGateway, provider = { "This had an error" })
122 				}
123 			}

```

### potential-bugs, ImplicitDefaultLocale (2)

Implicit default locale used for string processing. Consider using explicit locale.

[Documentation](https://detekt.dev/docs/rules/potential-bugs#implicitdefaultlocale)

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/viruscheck/VirusHashChecker.kt:47:31
```
String.format("%02x", byte) uses implicitly default locale for string formatting.
```
```kotlin
44 
45             val md5Hex = StringBuilder()
46             for (byte in digest) {
47                 md5Hex.append(String.format("%02x", byte))
!!                               ^ error
48             }
49 
50             return md5Hex.toString()

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/viruscheck/VirusHashChecker.kt:60:31
```
String.format("%02x", byte) uses implicitly default locale for string formatting.
```
```kotlin
57 
58             val md5Hex = StringBuilder()
59             for (byte in digest) {
60                 md5Hex.append(String.format("%02x", byte))
!!                               ^ error
61             }
62 
63             return md5Hex.toString()

```

### style, ForbiddenComment (1)

Flags a forbidden comment.

[Documentation](https://detekt.dev/docs/rules/style#forbiddencomment)

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/plugins/HTTP.kt:18:13
```
Forbidden TODO todo marker in comment, please do the changes.
```
```kotlin
15 		allowMethod(HttpMethod.Patch)
16 		allowHeader(HttpHeaders.Authorization)
17 		allowHeader("MyCustomHeader")
18 		anyHost() // @TODO: Don't do this in production if possible. Try to limit it.
!!             ^ error
19 	}
20 	routing {
21 		openAPI(path = "openapi")

```

### style, MagicNumber (8)

Report magic numbers. Magic number is a numeric literal that is not defined as a constant and hence it's unclear what the purpose of this number is. It's better to declare such numbers as constants and give them a proper name. By default, -1, 0, 1, and 2 are not considered to be magic numbers.

[Documentation](https://detekt.dev/docs/rules/style#magicnumber)

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/encryption/Encryption.kt:68:23
```
This expression contains a magic number. Consider defining it to a well named constant.
```
```kotlin
65       if (key == null) {
66         throw IllegalArgumentException("Key must not be null")
67       }
68       if (key.size != 32) {
!!                       ^ error
69         throw IllegalArgumentException("Key must be 32 bytes in length")
70       }
71       return SecretKeySpec(key, "AES")

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/encryption/PassKeyGeneration.kt:24:27
```
This expression contains a magic number. Consider defining it to a well named constant.
```
```kotlin
21             Specifications override default allowable characters*/
22 
23             //set password length
24             var passLen = 14 //recommended strong password length
!!                           ^ error
25             if (givenLen != null) {
26                 passLen = givenLen //client given password length
27             }

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/encryption/PassKeyGeneration.kt:75:27
```
This expression contains a magic number. Consider defining it to a well named constant.
```
```kotlin
72             /*Returns new passkey (optionally generated from password)*/
73 
74             //set password length
75             var passLen = 32 //recommended strong password length in bytes
!!                           ^ error
76             if (givenLen != null) {
77                 passLen = givenLen //client given password length
78             }

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/encryption/PassKeyGeneration.kt:86:30
```
This expression contains a magic number. Consider defining it to a well named constant.
```
```kotlin
83             }
84 
85             //passkey from password with PBKDF2 algorithm
86             val iterations = 120_000 //OWASP recommended iterations
!!                              ^ error
87             val chars = password.toCharArray()
88             val salt: ByteArray =
89                 givenSalt?.toByteArray() ?: getRandomBytes(passLen) //salt = given salt or random bytes

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/encryption/PassKeyGeneration.kt:91:70
```
This expression contains a magic number. Consider defining it to a well named constant.
```
```kotlin
88             val salt: ByteArray =
89                 givenSalt?.toByteArray() ?: getRandomBytes(passLen) //salt = given salt or random bytes
90 
91             val spec = PBEKeySpec(chars, salt, iterations, passLen * 8) //key length in bits
!!                                                                      ^ error
92             val skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")
93             var hash = skf.generateSecret(spec).encoded
94 

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/plugins/Routing.kt:83:51
```
This expression contains a magic number. Consider defining it to a well named constant.
```
```kotlin
80 		route("/password/Get/{len?}/{digits?}/{case?}/{specialChars?}", HttpMethod.Get) {
81 			handle {
82 				try {
83 					val len = call.parameters["len"]?.toInt() ?: 14
!!                                                   ^ error
84 					val digits = call.parameters["digits"]?.toBoolean() ?: false
85 					val casing = call.parameters["case"]?.toBoolean() ?: false
86 					val specialChars = call.parameters["specialChars"]?.toBoolean() ?: false

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/tokengen/JWTGenerator.kt:12:52
```
This expression contains a magic number. Consider defining it to a well named constant.
```
```kotlin
9  		.withAudience("jwt-audience")
10 		.withIssuer("https://jwt-provider-domain/")
11 		.withClaim("clientId", "sample")
12 		.withExpiresAt(Date(System.currentTimeMillis() + 60000000))
!!                                                    ^ error
13 		.sign(Algorithm.HMAC256("secret"))
14 	print(token)
15 }

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/viruscheck/VirusHashChecker.kt:36:40
```
This expression contains a magic number. Consider defining it to a well named constant.
```
```kotlin
33         private fun calculateMD5(file: File): String {
34             val md = MessageDigest.getInstance("MD5")
35             file.inputStream().use { input ->
36                 val buffer = ByteArray(8192)
!!                                        ^ error
37                 var bytesRead = input.read(buffer)
38                 while (bytesRead > 0) {
39                     md.update(buffer, 0, bytesRead)

```

### style, UnusedPrivateProperty (1)

Property is unused and should be removed.

[Documentation](https://detekt.dev/docs/rules/style#unusedprivateproperty)

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/encryption/PassKeyGeneration.kt:62:18
```
Private property `i` is unused.
```
```kotlin
59             var password = ""
60             val rand = SecureRandom() //create secure random int
61             var randInt: Int = rand.nextInt(characterList.length)
62             for (i: Int in 1.rangeTo(passLen)) {
!!                  ^ error
63                 password += characterList[randInt]
64                 randInt = rand.nextInt(characterList.length)
65             }

```

### style, UseRequire (10)

Use require() instead of throwing an IllegalArgumentException.

[Documentation](https://detekt.dev/docs/rules/style#userequire)

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/encryption/Encryption.kt:66:9
```
Use require() instead of throwing an IllegalArgumentException.
```
```kotlin
63     }
64     private fun validateAndPrepareKey(key: ByteArray?) : SecretKeySpec {
65       if (key == null) {
66         throw IllegalArgumentException("Key must not be null")
!!         ^ error
67       }
68       if (key.size != 32) {
69         throw IllegalArgumentException("Key must be 32 bytes in length")

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/encryption/Encryption.kt:69:9
```
Use require() instead of throwing an IllegalArgumentException.
```
```kotlin
66         throw IllegalArgumentException("Key must not be null")
67       }
68       if (key.size != 32) {
69         throw IllegalArgumentException("Key must be 32 bytes in length")
!!         ^ error
70       }
71       return SecretKeySpec(key, "AES")
72     }

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/persistence/FilePersister.kt:28:17
```
Use require() instead of throwing an IllegalArgumentException.
```
```kotlin
25                     return false //could not create file
26                 }
27             } else if (!overwrite) { //check if overwrite is allowed
28                 throw IllegalArgumentException("File already exists and overwrite is not allowed")
!!                 ^ error
29             }
30 
31             file.writeBytes(request.contents) //write contents to file

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/persistence/FilePersister.kt:47:17
```
Use require() instead of throwing an IllegalArgumentException.
```
```kotlin
44 
45             val file = File(path + File.separator + fileName)
46             if (!file.exists()) {
47                 throw IllegalArgumentException("File does not exist")
!!                 ^ error
48             }
49             return file.readBytes()
50         }

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/persistence/FilePersister.kt:62:17
```
Use require() instead of throwing an IllegalArgumentException.
```
```kotlin
59 
60             val file = File(path + File.separator + fileName)
61             if (!file.exists()) {
62                 throw IllegalArgumentException("File does not exist")
!!                 ^ error
63             }
64             return file.delete()
65         }

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/persistence/FilePersister.kt:74:21
```
Use require() instead of throwing an IllegalArgumentException.
```
```kotlin
71             var dir = File(path + File.separator + "SecurityTools")
72             if (!dir.isDirectory) { //check if directory exists
73                 if (!dir.mkdirs()) {  //create directory and check success
74                     throw IllegalArgumentException("Could not create directory")
!!                     ^ error
75                 }
76             }
77 

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/persistence/FilePersister.kt:87:17
```
Use require() instead of throwing an IllegalArgumentException.
```
```kotlin
84                 path = openCreateDir(path, clientID, create)
85             }
86             if (userID != null && clientID == null) {
87                 throw IllegalArgumentException("Client ID must be specified to use user ID")
!!                 ^ error
88             } else if (userID != null) {
89                 path = openCreateDir(path, userID, create)
90             }

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/persistence/FilePersister.kt:112:21
```
Use require() instead of throwing an IllegalArgumentException.
```
```kotlin
109             val dirNew = File(pathNew)
110             if (!dirNew.isDirectory) { //check if directory exists
111                 if (!create) {
112                     throw IllegalArgumentException("Directory does not exist")
!!!                     ^ error
113                 }
114                 if (!dirNew.mkdirs()) {  //create directory and check success
115                     throw IllegalArgumentException("Could not create directory")

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/persistence/FilePersister.kt:115:21
```
Use require() instead of throwing an IllegalArgumentException.
```
```kotlin
112                     throw IllegalArgumentException("Directory does not exist")
113                 }
114                 if (!dirNew.mkdirs()) {  //create directory and check success
115                     throw IllegalArgumentException("Could not create directory")
!!!                     ^ error
116                 }
117             }
118             return pathNew

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/viruscheck/VirusHashChecker.kt:12:17
```
Use require() instead of throwing an IllegalArgumentException.
```
```kotlin
9      companion object : VirusChecker {
10         override fun virusCheck(file: File): String {
11             if (!file.exists() || !file.isFile) {
12                 throw IllegalArgumentException("File does not exist")
!!                 ^ error
13             }
14 
15             val searchHash = calculateMD5(file)

```

### style, UtilityClassWithPublicConstructor (4)

The class declaration is unnecessary because it only contains utility functions. An object declaration should be used instead.

[Documentation](https://detekt.dev/docs/rules/style#utilityclasswithpublicconstructor)

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/encryption/Encryption.kt:9:1
```
The class Encryption only contains utility functions. Consider defining it as an object.
```
```kotlin
6  import javax.crypto.spec.SecretKeySpec
7  
8  
9  public class Encryption {
!  ^ error
10 
11   companion object : Encrypter {
12     override fun fileEncrypt(contents: ByteArray, key: ByteArray?, scheme: String?): ByteArray {

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/encryption/PassKeyGeneration.kt:11:1
```
The class PassKeyGeneration only contains utility functions. Consider defining it as an object.
```
```kotlin
8  import javax.crypto.spec.PBEKeySpec
9  
10 
11 public class PassKeyGeneration {
!! ^ error
12 
13     companion object : PassKeyGenerator {
14         override fun passwordGen(

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/persistence/FilePersister.kt:9:1
```
The class FilePersister only contains utility functions. Consider defining it as an object.
```
```kotlin
6  import java.util.*
7  
8  
9  public class FilePersister {
!  ^ error
10     //This is a static method
11     companion object : Persister {
12         override fun simpleFilePersist(

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/viruscheck/VirusHashChecker.kt:7:1
```
The class VirusHashChecker only contains utility functions. Consider defining it as an object.
```
```kotlin
4  import java.io.File
5  import java.security.MessageDigest
6  
7  public  class VirusHashChecker {
!  ^ error
8      //This is a static method
9      companion object : VirusChecker {
10         override fun virusCheck(file: File): String {

```

### style, WildcardImport (32)

Wildcard imports should be replaced with imports using fully qualified class names. Wildcard imports can lead to naming conflicts. A library update can introduce naming clashes with your classes which results in compilation errors.

[Documentation](https://detekt.dev/docs/rules/style#wildcardimport)

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/Application.kt:7:1
```
io.ktor.server.application.* is a wildcard import. Replace it with fully qualified imports.
```
```kotlin
4  import bytebandits.plugins.configureRouting
5  import bytebandits.plugins.configureSecurity
6  import bytebandits.plugins.configureSerialization
7  import io.ktor.server.application.*
!  ^ error
8  import io.ktor.server.engine.*
9  import io.ktor.server.netty.*
10 

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/Application.kt:8:1
```
io.ktor.server.engine.* is a wildcard import. Replace it with fully qualified imports.
```
```kotlin
5  import bytebandits.plugins.configureSecurity
6  import bytebandits.plugins.configureSerialization
7  import io.ktor.server.application.*
8  import io.ktor.server.engine.*
!  ^ error
9  import io.ktor.server.netty.*
10 
11 fun main() {

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/Application.kt:9:1
```
io.ktor.server.netty.* is a wildcard import. Replace it with fully qualified imports.
```
```kotlin
6  import bytebandits.plugins.configureSerialization
7  import io.ktor.server.application.*
8  import io.ktor.server.engine.*
9  import io.ktor.server.netty.*
!  ^ error
10 
11 fun main() {
12 	embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/plugins/HTTP.kt:3:1
```
io.ktor.http.* is a wildcard import. Replace it with fully qualified imports.
```
```kotlin
1 package bytebandits.plugins
2 
3 import io.ktor.http.*
! ^ error
4 import io.ktor.server.application.*
5 import io.ktor.server.plugins.cors.routing.*
6 import io.ktor.server.plugins.openapi.*

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/plugins/HTTP.kt:4:1
```
io.ktor.server.application.* is a wildcard import. Replace it with fully qualified imports.
```
```kotlin
1 package bytebandits.plugins
2 
3 import io.ktor.http.*
4 import io.ktor.server.application.*
! ^ error
5 import io.ktor.server.plugins.cors.routing.*
6 import io.ktor.server.plugins.openapi.*
7 import io.ktor.server.plugins.swagger.*

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/plugins/HTTP.kt:5:1
```
io.ktor.server.plugins.cors.routing.* is a wildcard import. Replace it with fully qualified imports.
```
```kotlin
2 
3 import io.ktor.http.*
4 import io.ktor.server.application.*
5 import io.ktor.server.plugins.cors.routing.*
! ^ error
6 import io.ktor.server.plugins.openapi.*
7 import io.ktor.server.plugins.swagger.*
8 import io.ktor.server.routing.*

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/plugins/HTTP.kt:6:1
```
io.ktor.server.plugins.openapi.* is a wildcard import. Replace it with fully qualified imports.
```
```kotlin
3  import io.ktor.http.*
4  import io.ktor.server.application.*
5  import io.ktor.server.plugins.cors.routing.*
6  import io.ktor.server.plugins.openapi.*
!  ^ error
7  import io.ktor.server.plugins.swagger.*
8  import io.ktor.server.routing.*
9  

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/plugins/HTTP.kt:7:1
```
io.ktor.server.plugins.swagger.* is a wildcard import. Replace it with fully qualified imports.
```
```kotlin
4  import io.ktor.server.application.*
5  import io.ktor.server.plugins.cors.routing.*
6  import io.ktor.server.plugins.openapi.*
7  import io.ktor.server.plugins.swagger.*
!  ^ error
8  import io.ktor.server.routing.*
9  
10 fun Application.configureHTTP() {

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/plugins/HTTP.kt:8:1
```
io.ktor.server.routing.* is a wildcard import. Replace it with fully qualified imports.
```
```kotlin
5  import io.ktor.server.plugins.cors.routing.*
6  import io.ktor.server.plugins.openapi.*
7  import io.ktor.server.plugins.swagger.*
8  import io.ktor.server.routing.*
!  ^ error
9  
10 fun Application.configureHTTP() {
11 	install(CORS) {

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/plugins/Routing.kt:7:1
```
io.ktor.http.* is a wildcard import. Replace it with fully qualified imports.
```
```kotlin
4  import bytebandits.models.SimpleFileRequest
5  import bytebandits.models.WebSimpleFileRequest
6  import bytebandits.persistence.FilePersister
7  import io.ktor.http.*
!  ^ error
8  import io.ktor.server.application.*
9  import io.ktor.server.auth.*
10 import io.ktor.server.auth.jwt.*

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/plugins/Routing.kt:8:1
```
io.ktor.server.application.* is a wildcard import. Replace it with fully qualified imports.
```
```kotlin
5  import bytebandits.models.WebSimpleFileRequest
6  import bytebandits.persistence.FilePersister
7  import io.ktor.http.*
8  import io.ktor.server.application.*
!  ^ error
9  import io.ktor.server.auth.*
10 import io.ktor.server.auth.jwt.*
11 import io.ktor.server.plugins.swagger.*

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/plugins/Routing.kt:9:1
```
io.ktor.server.auth.* is a wildcard import. Replace it with fully qualified imports.
```
```kotlin
6  import bytebandits.persistence.FilePersister
7  import io.ktor.http.*
8  import io.ktor.server.application.*
9  import io.ktor.server.auth.*
!  ^ error
10 import io.ktor.server.auth.jwt.*
11 import io.ktor.server.plugins.swagger.*
12 import io.ktor.server.request.*

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/plugins/Routing.kt:10:1
```
io.ktor.server.auth.jwt.* is a wildcard import. Replace it with fully qualified imports.
```
```kotlin
7  import io.ktor.http.*
8  import io.ktor.server.application.*
9  import io.ktor.server.auth.*
10 import io.ktor.server.auth.jwt.*
!! ^ error
11 import io.ktor.server.plugins.swagger.*
12 import io.ktor.server.request.*
13 import io.ktor.server.response.*

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/plugins/Routing.kt:11:1
```
io.ktor.server.plugins.swagger.* is a wildcard import. Replace it with fully qualified imports.
```
```kotlin
8  import io.ktor.server.application.*
9  import io.ktor.server.auth.*
10 import io.ktor.server.auth.jwt.*
11 import io.ktor.server.plugins.swagger.*
!! ^ error
12 import io.ktor.server.request.*
13 import io.ktor.server.response.*
14 import io.ktor.server.routing.*

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/plugins/Routing.kt:12:1
```
io.ktor.server.request.* is a wildcard import. Replace it with fully qualified imports.
```
```kotlin
9  import io.ktor.server.auth.*
10 import io.ktor.server.auth.jwt.*
11 import io.ktor.server.plugins.swagger.*
12 import io.ktor.server.request.*
!! ^ error
13 import io.ktor.server.response.*
14 import io.ktor.server.routing.*
15 import org.slf4j.LoggerFactory

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/plugins/Routing.kt:13:1
```
io.ktor.server.response.* is a wildcard import. Replace it with fully qualified imports.
```
```kotlin
10 import io.ktor.server.auth.jwt.*
11 import io.ktor.server.plugins.swagger.*
12 import io.ktor.server.request.*
13 import io.ktor.server.response.*
!! ^ error
14 import io.ktor.server.routing.*
15 import org.slf4j.LoggerFactory
16 import java.util.*

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/plugins/Routing.kt:14:1
```
io.ktor.server.routing.* is a wildcard import. Replace it with fully qualified imports.
```
```kotlin
11 import io.ktor.server.plugins.swagger.*
12 import io.ktor.server.request.*
13 import io.ktor.server.response.*
14 import io.ktor.server.routing.*
!! ^ error
15 import org.slf4j.LoggerFactory
16 import java.util.*
17 

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/plugins/Security.kt:5:1
```
io.ktor.http.* is a wildcard import. Replace it with fully qualified imports.
```
```kotlin
2 
3 import com.auth0.jwt.JWT
4 import com.auth0.jwt.algorithms.Algorithm
5 import io.ktor.http.*
! ^ error
6 import io.ktor.server.application.*
7 import io.ktor.server.auth.*
8 import io.ktor.server.auth.jwt.*

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/plugins/Security.kt:6:1
```
io.ktor.server.application.* is a wildcard import. Replace it with fully qualified imports.
```
```kotlin
3  import com.auth0.jwt.JWT
4  import com.auth0.jwt.algorithms.Algorithm
5  import io.ktor.http.*
6  import io.ktor.server.application.*
!  ^ error
7  import io.ktor.server.auth.*
8  import io.ktor.server.auth.jwt.*
9  import io.ktor.server.response.*

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/plugins/Security.kt:7:1
```
io.ktor.server.auth.* is a wildcard import. Replace it with fully qualified imports.
```
```kotlin
4  import com.auth0.jwt.algorithms.Algorithm
5  import io.ktor.http.*
6  import io.ktor.server.application.*
7  import io.ktor.server.auth.*
!  ^ error
8  import io.ktor.server.auth.jwt.*
9  import io.ktor.server.response.*
10 

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/plugins/Security.kt:8:1
```
io.ktor.server.auth.jwt.* is a wildcard import. Replace it with fully qualified imports.
```
```kotlin
5  import io.ktor.http.*
6  import io.ktor.server.application.*
7  import io.ktor.server.auth.*
8  import io.ktor.server.auth.jwt.*
!  ^ error
9  import io.ktor.server.response.*
10 
11 fun Application.configureSecurity() {

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/plugins/Security.kt:9:1
```
io.ktor.server.response.* is a wildcard import. Replace it with fully qualified imports.
```
```kotlin
6  import io.ktor.server.application.*
7  import io.ktor.server.auth.*
8  import io.ktor.server.auth.jwt.*
9  import io.ktor.server.response.*
!  ^ error
10 
11 fun Application.configureSecurity() {
12 	// Please read the jwt property from the config file if you are using EngineMain

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/plugins/Serialization.kt:3:1
```
io.ktor.serialization.gson.* is a wildcard import. Replace it with fully qualified imports.
```
```kotlin
1 package bytebandits.plugins
2 
3 import io.ktor.serialization.gson.*
! ^ error
4 import io.ktor.serialization.kotlinx.json.*
5 import io.ktor.server.application.*
6 import io.ktor.server.plugins.contentnegotiation.*

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/plugins/Serialization.kt:4:1
```
io.ktor.serialization.kotlinx.json.* is a wildcard import. Replace it with fully qualified imports.
```
```kotlin
1 package bytebandits.plugins
2 
3 import io.ktor.serialization.gson.*
4 import io.ktor.serialization.kotlinx.json.*
! ^ error
5 import io.ktor.server.application.*
6 import io.ktor.server.plugins.contentnegotiation.*
7 import io.ktor.server.routing.*

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/plugins/Serialization.kt:5:1
```
io.ktor.server.application.* is a wildcard import. Replace it with fully qualified imports.
```
```kotlin
2 
3 import io.ktor.serialization.gson.*
4 import io.ktor.serialization.kotlinx.json.*
5 import io.ktor.server.application.*
! ^ error
6 import io.ktor.server.plugins.contentnegotiation.*
7 import io.ktor.server.routing.*
8 

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/plugins/Serialization.kt:6:1
```
io.ktor.server.plugins.contentnegotiation.* is a wildcard import. Replace it with fully qualified imports.
```
```kotlin
3  import io.ktor.serialization.gson.*
4  import io.ktor.serialization.kotlinx.json.*
5  import io.ktor.server.application.*
6  import io.ktor.server.plugins.contentnegotiation.*
!  ^ error
7  import io.ktor.server.routing.*
8  
9  fun Application.configureSerialization() {

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/main/kotlin/bytebandits/plugins/Serialization.kt:7:1
```
io.ktor.server.routing.* is a wildcard import. Replace it with fully qualified imports.
```
```kotlin
4  import io.ktor.serialization.kotlinx.json.*
5  import io.ktor.server.application.*
6  import io.ktor.server.plugins.contentnegotiation.*
7  import io.ktor.server.routing.*
!  ^ error
8  
9  fun Application.configureSerialization() {
10 	install(ContentNegotiation) {

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/test/kotlin/bytebandits/encryption/EncryptionTests.kt:3:1
```
kotlin.test.* is a wildcard import. Replace it with fully qualified imports.
```
```kotlin
1 package bytebandits.encryption
2 
3 import kotlin.test.*
! ^ error
4 
5 class EncryptionTests{
6   @Test

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/test/kotlin/bytebandits/encryption/PassKeyGenerationTests.kt:3:1
```
kotlin.test.* is a wildcard import. Replace it with fully qualified imports.
```
```kotlin
1 package bytebandits.encryption
2 
3 import kotlin.test.*
! ^ error
4 
5 class PassKeyGenerationTests {
6 

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/test/kotlin/bytebandits/routing/RoutingTests.kt:4:1
```
io.ktor.http.* is a wildcard import. Replace it with fully qualified imports.
```
```kotlin
1 package bytebandits.routing
2 
3 import bytebandits.module
4 import io.ktor.http.*
! ^ error
5 import io.ktor.server.application.*
6 import io.ktor.server.testing.*
7 import kotlin.test.Test

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/test/kotlin/bytebandits/routing/RoutingTests.kt:5:1
```
io.ktor.server.application.* is a wildcard import. Replace it with fully qualified imports.
```
```kotlin
2 
3 import bytebandits.module
4 import io.ktor.http.*
5 import io.ktor.server.application.*
! ^ error
6 import io.ktor.server.testing.*
7 import kotlin.test.Test
8 import kotlin.test.assertEquals

```

* C:/Users/hugom/OneDrive/CUiNYC/Classes/2023-F/CS4156/Byte-Bandits-ASWE/byte-bandits/src/test/kotlin/bytebandits/routing/RoutingTests.kt:6:1
```
io.ktor.server.testing.* is a wildcard import. Replace it with fully qualified imports.
```
```kotlin
3  import bytebandits.module
4  import io.ktor.http.*
5  import io.ktor.server.application.*
6  import io.ktor.server.testing.*
!  ^ error
7  import kotlin.test.Test
8  import kotlin.test.assertEquals
9  import kotlin.test.assertTrue

```

generated with [detekt version 1.23.1](https://detekt.dev/) on 2023-10-20 02:57:49 UTC
