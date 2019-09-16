local lockKey = KEYS[1]

-- get info
local result_1 = redis.call('GET', lockKey)
if tonumber(result_1) < 10000
then
local result_2= redis.call('INCR', lockKey)
return result_1
else
return result_1
end