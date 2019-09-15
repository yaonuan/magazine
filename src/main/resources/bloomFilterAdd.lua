local lockKey = KEYS[1]
local lockValue = KEYS[2]

-- bloomFilter
local result_1 = redis.call('BF.ADD', lockKey, lockValue)
return result_1